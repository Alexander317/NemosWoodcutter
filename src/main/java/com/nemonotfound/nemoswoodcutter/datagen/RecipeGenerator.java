package com.nemonotfound.nemoswoodcutter.datagen;

import com.mojang.datafixers.util.Pair;
import com.nemonotfound.nemoswoodcutter.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.VanillaRecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {

    public RecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        createWoodCutterRecipe(exporter);

        createCarpentryRecipe(exporter, Pair.of(Blocks.ACACIA_PLANKS, 2), Items.ACACIA_BOAT, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.ACACIA_PLANKS, 1), Items.ACACIA_BUTTON, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.ACACIA_PLANKS, 2), Items.ACACIA_DOOR, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.ACACIA_PLANKS, 1), Items.ACACIA_FENCE_GATE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.ACACIA_PLANKS, 1), Items.ACACIA_FENCE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.ACACIA_PLANKS, 1), Items.ACACIA_PRESSURE_PLATE, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.ACACIA_PLANKS, 1), Items.ACACIA_SIGN, 3);
        createCarpentryRecipe(exporter, Pair.of(Blocks.ACACIA_PLANKS, 1), Items.ACACIA_SLAB, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.ACACIA_PLANKS, 1), Items.ACACIA_STAIRS, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.ACACIA_PLANKS, 1), Items.ACACIA_TRAPDOOR, 2);

        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_PLANKS, 2), Items.BAMBOO_RAFT, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_PLANKS, 1), Items.BAMBOO_BUTTON, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_PLANKS, 2), Items.BAMBOO_DOOR, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_PLANKS, 1), Items.BAMBOO_FENCE_GATE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_PLANKS, 1), Items.BAMBOO_FENCE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_PLANKS, 1), Items.BAMBOO_MOSAIC_SLAB, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_PLANKS, 1), Items.BAMBOO_MOSAIC_STAIRS, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_PLANKS, 1), Items.BAMBOO_MOSAIC, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_PLANKS, 1), Items.BAMBOO_PRESSURE_PLATE, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_PLANKS, 1), Items.BAMBOO_SIGN, 3);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_PLANKS, 1), Items.BAMBOO_SLAB, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_PLANKS, 1), Items.BAMBOO_STAIRS, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_PLANKS, 1), Items.BAMBOO_TRAPDOOR, 2);

        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_MOSAIC, 1), Items.BAMBOO_MOSAIC_SLAB, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BAMBOO_MOSAIC, 1), Items.BAMBOO_MOSAIC_STAIRS, 1);

        createCarpentryRecipe(exporter, Pair.of(Blocks.BIRCH_PLANKS, 2), Items.BIRCH_BOAT, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BIRCH_PLANKS, 1), Items.BIRCH_BUTTON, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BIRCH_PLANKS, 2), Items.BIRCH_DOOR, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BIRCH_PLANKS, 1), Items.BIRCH_FENCE_GATE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BIRCH_PLANKS, 1), Items.BIRCH_FENCE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BIRCH_PLANKS, 1), Items.BIRCH_PRESSURE_PLATE, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BIRCH_PLANKS, 1), Items.BIRCH_SIGN, 3);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BIRCH_PLANKS, 1), Items.BIRCH_SLAB, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BIRCH_PLANKS, 1), Items.BIRCH_STAIRS, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.BIRCH_PLANKS, 1), Items.BIRCH_TRAPDOOR, 2);

        createCarpentryRecipe(exporter, Pair.of(Blocks.CHERRY_PLANKS, 2), Items.CHERRY_BOAT, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CHERRY_PLANKS, 1), Items.CHERRY_BUTTON, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CHERRY_PLANKS, 2), Items.CHERRY_DOOR, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CHERRY_PLANKS, 1), Items.CHERRY_FENCE_GATE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CHERRY_PLANKS, 1), Items.CHERRY_FENCE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CHERRY_PLANKS, 1), Items.CHERRY_PRESSURE_PLATE, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CHERRY_PLANKS, 1), Items.CHERRY_SIGN, 3);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CHERRY_PLANKS, 1), Items.CHERRY_SLAB, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CHERRY_PLANKS, 1), Items.CHERRY_STAIRS, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CHERRY_PLANKS, 1), Items.CHERRY_TRAPDOOR, 2);

        createCarpentryRecipe(exporter, Pair.of(Blocks.CRIMSON_PLANKS, 1), Items.CRIMSON_BUTTON, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CRIMSON_PLANKS, 2), Items.CRIMSON_DOOR, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CRIMSON_PLANKS, 1), Items.CRIMSON_FENCE_GATE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CRIMSON_PLANKS, 1), Items.CRIMSON_FENCE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CRIMSON_PLANKS, 1), Items.CRIMSON_PRESSURE_PLATE, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CRIMSON_PLANKS, 1), Items.CRIMSON_SIGN, 3);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CRIMSON_PLANKS, 1), Items.CRIMSON_SLAB, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CRIMSON_PLANKS, 1), Items.CRIMSON_STAIRS, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.CRIMSON_PLANKS, 1), Items.CRIMSON_TRAPDOOR, 2);

        createCarpentryRecipe(exporter, Pair.of(Blocks.DARK_OAK_PLANKS, 2), Items.DARK_OAK_BOAT, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.DARK_OAK_PLANKS, 1), Items.DARK_OAK_BUTTON, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.DARK_OAK_PLANKS, 2), Items.DARK_OAK_DOOR, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.DARK_OAK_PLANKS, 1), Items.DARK_OAK_FENCE_GATE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.DARK_OAK_PLANKS, 1), Items.DARK_OAK_FENCE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.DARK_OAK_PLANKS, 1), Items.DARK_OAK_PRESSURE_PLATE, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.DARK_OAK_PLANKS, 1), Items.DARK_OAK_SIGN, 3);
        createCarpentryRecipe(exporter, Pair.of(Blocks.DARK_OAK_PLANKS, 1), Items.DARK_OAK_SLAB, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.DARK_OAK_PLANKS, 1), Items.DARK_OAK_STAIRS, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.DARK_OAK_PLANKS, 1), Items.DARK_OAK_TRAPDOOR, 2);

        createCarpentryRecipe(exporter, Pair.of(Blocks.JUNGLE_PLANKS, 2), Items.JUNGLE_BOAT, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.JUNGLE_PLANKS, 1), Items.JUNGLE_BUTTON, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.JUNGLE_PLANKS, 2), Items.JUNGLE_DOOR, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.JUNGLE_PLANKS, 1), Items.JUNGLE_FENCE_GATE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.JUNGLE_PLANKS, 1), Items.JUNGLE_FENCE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.JUNGLE_PLANKS, 1), Items.JUNGLE_PRESSURE_PLATE, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.JUNGLE_PLANKS, 1), Items.JUNGLE_SIGN, 3);
        createCarpentryRecipe(exporter, Pair.of(Blocks.JUNGLE_PLANKS, 1), Items.JUNGLE_SLAB, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.JUNGLE_PLANKS, 1), Items.JUNGLE_STAIRS, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.JUNGLE_PLANKS, 1), Items.JUNGLE_TRAPDOOR, 2);

        createCarpentryRecipe(exporter, Pair.of(Blocks.MANGROVE_PLANKS, 2), Items.MANGROVE_BOAT, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.MANGROVE_PLANKS, 1), Items.MANGROVE_BUTTON, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.MANGROVE_PLANKS, 2), Items.MANGROVE_DOOR, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.MANGROVE_PLANKS, 1), Items.MANGROVE_FENCE_GATE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.MANGROVE_PLANKS, 1), Items.MANGROVE_FENCE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.MANGROVE_PLANKS, 1), Items.MANGROVE_PRESSURE_PLATE, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.MANGROVE_PLANKS, 1), Items.MANGROVE_SIGN, 3);
        createCarpentryRecipe(exporter, Pair.of(Blocks.MANGROVE_PLANKS, 1), Items.MANGROVE_SLAB, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.MANGROVE_PLANKS, 1), Items.MANGROVE_STAIRS, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.MANGROVE_PLANKS, 1), Items.MANGROVE_TRAPDOOR, 2);

        createCarpentryRecipe(exporter, Pair.of(Blocks.OAK_PLANKS, 2), Items.OAK_BOAT, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.OAK_PLANKS, 1), Items.OAK_BUTTON, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.OAK_PLANKS, 2), Items.OAK_DOOR, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.OAK_PLANKS, 1), Items.OAK_FENCE_GATE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.OAK_PLANKS, 1), Items.OAK_FENCE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.OAK_PLANKS, 1), Items.LADDER, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.OAK_PLANKS, 1), Items.OAK_PRESSURE_PLATE, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.OAK_PLANKS, 1), Items.OAK_SIGN, 3);
        createCarpentryRecipe(exporter, Pair.of(Blocks.OAK_PLANKS, 1), Items.OAK_SLAB, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.OAK_PLANKS, 1), Items.OAK_STAIRS, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.OAK_PLANKS, 1), Items.OAK_TRAPDOOR, 2);

        createCarpentryRecipe(exporter, Pair.of(Blocks.SPRUCE_PLANKS, 2), Items.SPRUCE_BOAT, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.SPRUCE_PLANKS, 1), Items.SPRUCE_BUTTON, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.SPRUCE_PLANKS, 2), Items.SPRUCE_DOOR, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.SPRUCE_PLANKS, 1), Items.SPRUCE_FENCE_GATE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.SPRUCE_PLANKS, 1), Items.SPRUCE_FENCE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.SPRUCE_PLANKS, 1), Items.SPRUCE_PRESSURE_PLATE, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.SPRUCE_PLANKS, 1), Items.SPRUCE_SIGN, 3);
        createCarpentryRecipe(exporter, Pair.of(Blocks.SPRUCE_PLANKS, 1), Items.SPRUCE_SLAB, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.SPRUCE_PLANKS, 1), Items.SPRUCE_STAIRS, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.SPRUCE_PLANKS, 1), Items.SPRUCE_TRAPDOOR, 2);

        createCarpentryRecipe(exporter, Pair.of(Blocks.WARPED_PLANKS, 1), Items.WARPED_BUTTON, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.WARPED_PLANKS, 2), Items.WARPED_DOOR, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.WARPED_PLANKS, 1), Items.WARPED_FENCE_GATE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.WARPED_PLANKS, 1), Items.WARPED_FENCE, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.WARPED_PLANKS, 1), Items.WARPED_PRESSURE_PLATE, 4);
        createCarpentryRecipe(exporter, Pair.of(Blocks.WARPED_PLANKS, 1), Items.WARPED_SIGN, 3);
        createCarpentryRecipe(exporter, Pair.of(Blocks.WARPED_PLANKS, 1), Items.WARPED_SLAB, 2);
        createCarpentryRecipe(exporter, Pair.of(Blocks.WARPED_PLANKS, 1), Items.WARPED_STAIRS, 1);
        createCarpentryRecipe(exporter, Pair.of(Blocks.WARPED_PLANKS, 1), Items.WARPED_TRAPDOOR, 2);
    }

    private void createCarpentryRecipe(RecipeExporter exporter, Pair<Block, Integer> ingredientPair, Item output,
                                       int outputCount) {
        Block input = ingredientPair.getFirst();
        String blockName = Registries.BLOCK.getId(input).getPath();

        WoodcuttingRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, output, outputCount)
                .input(Ingredient.ofItems(input), ingredientPair.getSecond())
                .criterion(FabricRecipeProvider.hasItem(output), conditionsFromItem(output))
                .offerTo(exporter, new Identifier(blockName + "_to_" + getRecipeName(output) + "_woodcutting"));
    }

    private void createWoodCutterRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.WOODCUTTER_BLOCK)
                .input('I', Items.IRON_INGOT)
                .input('#', ItemTags.LOGS)
                .pattern(" I ")
                .pattern("###")
                .criterion("has_logs", VanillaRecipeProvider.conditionsFromTag(ItemTags.LOGS))
                .offerTo(exporter);
    }
}