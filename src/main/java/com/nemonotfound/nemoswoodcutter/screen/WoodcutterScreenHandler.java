package com.nemonotfound.nemoswoodcutter.screen;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.nemonotfound.nemoswoodcutter.NemosWoodcutter;
import com.nemonotfound.nemoswoodcutter.block.ModBlocks;
import com.nemonotfound.nemoswoodcutter.recipe.ModRecipeTypes;
import com.nemonotfound.nemoswoodcutter.recipe.WoodcuttingRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

//TODO: Needs refactoring
public class WoodcutterScreenHandler
extends ScreenHandler {

    private final ScreenHandlerContext context;
    private final Property selectedRecipe = Property.create();
    private final World world;
    private List<RecipeEntry<WoodcuttingRecipe>> availableRecipes = Lists.newArrayList();
    private ItemStack inputStack = ItemStack.EMPTY;
    long lastTakeTime;
    final Slot inputSlot;
    final Slot outputSlot;
    Runnable contentsChangedListener = () -> {};
    public final Inventory input = new SimpleInventory(1){

        @Override
        public void markDirty() {
            super.markDirty();
            WoodcutterScreenHandler.this.onContentChanged(this);
            WoodcutterScreenHandler.this.contentsChangedListener.run();
        }
    };

    final CraftingResultInventory output = new CraftingResultInventory();

    public WoodcutterScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public WoodcutterScreenHandler(int syncId, PlayerInventory playerInventory, final ScreenHandlerContext context) {
        super(NemosWoodcutter.WOODCUTTER_SCREEN_HANDLER, syncId);
        int i;
        this.context = context;
        this.world = playerInventory.player.getWorld();
        this.inputSlot = this.addSlot(new Slot(this.input, 0, 20, 33));
        this.outputSlot = this.addSlot(new Slot(this.output, 1, 143, 33) {

            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                WoodcuttingRecipe recipe = availableRecipes.get(selectedRecipe.get()).value();
                stack.onCraftByPlayer(player.getWorld(), player, stack.getCount());
                WoodcutterScreenHandler.this.output.unlockLastRecipe(player, this.getInputStacks());
                Pair<Ingredient, Integer> ingredientPair = recipe.getIngredientPair();
                int requiredCount = ingredientPair.getSecond();
                ItemStack itemStack = WoodcutterScreenHandler.this.inputSlot.takeStack(requiredCount);

                if (!itemStack.isEmpty()) {
                    WoodcutterScreenHandler.this.populateResult();
                }
                context.run((world, pos) -> {
                    long l = world.getTime();
                    if (WoodcutterScreenHandler.this.lastTakeTime != l) {
                        world.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT,
                                SoundCategory.BLOCKS, 1.0f, 1.0f);
                        WoodcutterScreenHandler.this.lastTakeTime = l;
                    }
                });
                super.onTakeItem(player, stack);
            }

            private List<ItemStack> getInputStacks() {
                return List.of(WoodcutterScreenHandler.this.inputSlot.getStack());
            }
        });

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

        this.addProperty(this.selectedRecipe);
    }

    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }

    public List<RecipeEntry<WoodcuttingRecipe>> getAvailableRecipes() {
        return this.availableRecipes;
    }

    public int getAvailableRecipeCount() {
        return this.availableRecipes.size();
    }

    public boolean canCraft() {
        return this.inputSlot.hasStack() && !this.availableRecipes.isEmpty();
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return WoodcutterScreenHandler.canUse(this.context, player, ModBlocks.WOODCUTTER_BLOCK);
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (this.isInBounds(id)) {
            this.selectedRecipe.set(id);
            this.populateResult();
        }

        return true;
    }

    private boolean isInBounds(int id) {
        return id >= 0 && id < this.availableRecipes.size();
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        ItemStack itemStack = this.inputSlot.getStack();

        if (!itemStack.isOf(this.inputStack.getItem())) {
            this.inputStack = itemStack.copy();
            this.updateInput(inventory, itemStack);
        }
    }

    private void updateInput(Inventory input, ItemStack stack) {
        this.availableRecipes.clear();
        this.selectedRecipe.set(-1);
        this.outputSlot.setStackNoCallbacks(ItemStack.EMPTY);

        if (!stack.isEmpty()) {
            this.availableRecipes = new ArrayList<>(this.world.getRecipeManager()
                    .getAllMatches(ModRecipeTypes.WOODCUTTING, createRecipeInput(input), this.world).stream()
                    .filter(recipe -> !recipe.value().getResult(this.world.getRegistryManager()).toString().contains("0 air"))
                    .toList());
        }
    }

    private static SingleStackRecipeInput createRecipeInput(Inventory inventory) {
        return new SingleStackRecipeInput(inventory.getStack(0));
    }

    private void populateResult() {
        if (!this.availableRecipes.isEmpty() && this.isInBounds(this.selectedRecipe.get())) {
            RecipeEntry<WoodcuttingRecipe> recipeEntry = this.availableRecipes.get(this.selectedRecipe.get());
            WoodcuttingRecipe recipe = recipeEntry.value();
            Pair<Ingredient, Integer> ingredientPair = recipe.getIngredientPair();
            int requiredInputCount = ingredientPair.getSecond();
            var inputCount = inputSlot.getStack().getCount();

            ItemStack itemStack = recipeEntry.value().craft(createRecipeInput(this.input), this.world.getRegistryManager());

            if (inputCount < requiredInputCount) {
                this.outputSlot.setStackNoCallbacks(ItemStack.EMPTY);
            } else if (itemStack.isItemEnabled(this.world.getEnabledFeatures())) {
                this.output.setLastRecipe(recipeEntry);
                this.outputSlot.setStackNoCallbacks(itemStack);
            } else {
                this.outputSlot.setStackNoCallbacks(ItemStack.EMPTY);
            }
        } else {
            this.outputSlot.setStackNoCallbacks(ItemStack.EMPTY);
        }
        this.sendContentUpdates();
    }

    @Override
    public ScreenHandlerType<?> getType() {
        return NemosWoodcutter.WOODCUTTER_SCREEN_HANDLER;
    }

    public void setContentsChangedListener(Runnable contentsChangedListener) {
        this.contentsChangedListener = contentsChangedListener;
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.output && super.canInsertIntoSlot(stack, slot);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2 != null && slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            Item item = itemStack2.getItem();
            itemStack = itemStack2.copy();
            if (slot == 1) {
                item.onCraftByPlayer(itemStack2, player.getWorld(), player);
                if (!this.insertItem(itemStack2, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
                slot2.onQuickTransfer(itemStack2, itemStack);
            } else if (slot == 0 ? !this.insertItem(itemStack2, 2, 38, false) :
                    (this.world.getRecipeManager().getFirstMatch(ModRecipeTypes.WOODCUTTING,
                            new SingleStackRecipeInput(itemStack2), this.world).isPresent() ?
                            !this.insertItem(itemStack2, 0, 1, false) : (slot >= 2 && slot < 29 ?
                                    !this.insertItem(itemStack2, 29, 38, false) :
                            slot >= 29 && slot < 38 && !this.insertItem(itemStack2, 2, 29, false)))) {
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            }
            slot2.markDirty();
            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot2.onTakeItem(player, itemStack);
            this.sendContentUpdates();
        }
        return itemStack;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.output.removeStack(1);
        this.context.run((world, pos) -> this.dropInventory(player, this.input));
    }
}

