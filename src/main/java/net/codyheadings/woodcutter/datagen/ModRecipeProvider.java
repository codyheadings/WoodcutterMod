package net.codyheadings.woodcutter.datagen;

import net.codyheadings.woodcutter.Woodcutter;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Stream;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private record WoodSet(String name, Item wood, Item stripped_wood, Item log, Item stripped_log, Item planks, Item stairs, Item slab, Item shelf, Item fence, Item gate, Item trapdoor, Item door, Item sign, Item button, Item sticks) {

        // Bamboo has unique a plank type and no 'wood'
        boolean isBamboo() { return wood == null; }
        List<Item> loglikes() {
            return Stream.of(log, stripped_log, wood, stripped_wood)
                    .filter(Objects::nonNull)
                    .toList();
        }
    }

    private record Product(Function<WoodSet, Item> item, int fromLog, int fromPlanks, int salvage) {}

    private static final List<Product> PRODUCTS = List.of(
            new Product(WoodSet::planks, 4,0, 0),
            new Product(WoodSet::stairs, 4,1, 1),
            new Product(WoodSet::slab, 8,2, 1),
            new Product(WoodSet::shelf,2,0, 4),
            new Product(WoodSet::fence,4,1, 1),
            new Product(WoodSet::gate, 4,1, 1),
            new Product(WoodSet::door, 4,1, 1),
            new Product(WoodSet::trapdoor, 12, 3, 1),
            new Product(WoodSet::sign, 4,1, 1),
            new Product(WoodSet::button, 16, 4, 0),
            new Product(WoodSet::sticks, 12, 3, 0)
    );

    private static final List<WoodSet> WOODS = List.of(
            new WoodSet("oak", Items.OAK_WOOD, Items.STRIPPED_OAK_WOOD, Items.OAK_LOG, Items.STRIPPED_OAK_LOG, Items.OAK_PLANKS, Items.OAK_STAIRS, Items.OAK_SLAB, Items.OAK_SHELF, Items.OAK_FENCE, Items.OAK_FENCE_GATE, Items.OAK_TRAPDOOR, Items.OAK_DOOR, Items.OAK_SIGN, Items.OAK_BUTTON, Items.STICK),
            new WoodSet("spruce", Items.SPRUCE_WOOD, Items.STRIPPED_SPRUCE_WOOD, Items.SPRUCE_LOG, Items.STRIPPED_SPRUCE_LOG, Items.SPRUCE_PLANKS, Items.SPRUCE_STAIRS, Items.SPRUCE_SLAB, Items.SPRUCE_SHELF, Items.SPRUCE_FENCE, Items.SPRUCE_FENCE_GATE, Items.SPRUCE_TRAPDOOR, Items.SPRUCE_DOOR, Items.SPRUCE_SIGN, Items.SPRUCE_BUTTON, Items.STICK),
            new WoodSet("birch", Items.BIRCH_WOOD, Items.STRIPPED_BIRCH_WOOD, Items.BIRCH_LOG,Items.STRIPPED_BIRCH_LOG, Items.BIRCH_PLANKS, Items.BIRCH_STAIRS, Items.BIRCH_SLAB, Items.BIRCH_SHELF, Items.BIRCH_FENCE, Items.BIRCH_FENCE_GATE, Items.BIRCH_TRAPDOOR, Items.BIRCH_DOOR, Items.BIRCH_SIGN, Items.BIRCH_BUTTON, Items.STICK),
            new WoodSet("jungle", Items.JUNGLE_WOOD, Items.STRIPPED_JUNGLE_WOOD, Items.JUNGLE_LOG, Items.STRIPPED_JUNGLE_LOG, Items.JUNGLE_PLANKS, Items.JUNGLE_STAIRS, Items.JUNGLE_SLAB, Items.JUNGLE_SHELF, Items.JUNGLE_FENCE, Items.JUNGLE_FENCE_GATE, Items.JUNGLE_TRAPDOOR, Items.JUNGLE_DOOR, Items.JUNGLE_SIGN, Items.JUNGLE_BUTTON, Items.STICK),
            new WoodSet("dark_oak", Items.DARK_OAK_WOOD, Items.STRIPPED_DARK_OAK_WOOD, Items.DARK_OAK_LOG, Items.STRIPPED_DARK_OAK_LOG, Items.DARK_OAK_PLANKS, Items.DARK_OAK_STAIRS, Items.DARK_OAK_SLAB, Items.DARK_OAK_SHELF, Items.DARK_OAK_FENCE, Items.DARK_OAK_FENCE_GATE, Items.DARK_OAK_TRAPDOOR, Items.DARK_OAK_DOOR, Items.DARK_OAK_SIGN, Items.DARK_OAK_BUTTON, Items.STICK),
            new WoodSet("acacia", Items.ACACIA_WOOD, Items.STRIPPED_ACACIA_WOOD, Items.ACACIA_LOG, Items.STRIPPED_ACACIA_LOG, Items.ACACIA_PLANKS, Items.ACACIA_STAIRS, Items.ACACIA_SLAB, Items.ACACIA_SHELF, Items.ACACIA_FENCE, Items.ACACIA_FENCE_GATE, Items.ACACIA_TRAPDOOR, Items.ACACIA_DOOR, Items.ACACIA_SIGN, Items.ACACIA_BUTTON, Items.STICK),
            new WoodSet("pale_oak", Items.PALE_OAK_WOOD, Items.STRIPPED_PALE_OAK_WOOD, Items.PALE_OAK_LOG, Items.STRIPPED_PALE_OAK_LOG, Items.PALE_OAK_PLANKS, Items.PALE_OAK_STAIRS, Items.PALE_OAK_SLAB, Items.PALE_OAK_SHELF, Items.PALE_OAK_FENCE, Items.PALE_OAK_FENCE_GATE, Items.PALE_OAK_TRAPDOOR, Items.PALE_OAK_DOOR, Items.PALE_OAK_SIGN, Items.PALE_OAK_BUTTON, Items.STICK),
            new WoodSet("cherry", Items.CHERRY_WOOD, Items.STRIPPED_CHERRY_WOOD, Items.CHERRY_LOG, Items.STRIPPED_CHERRY_LOG, Items.CHERRY_PLANKS, Items.CHERRY_STAIRS, Items.CHERRY_SLAB, Items.CHERRY_SHELF, Items.CHERRY_FENCE, Items.CHERRY_FENCE_GATE, Items.CHERRY_TRAPDOOR, Items.CHERRY_DOOR, Items.CHERRY_SIGN, Items.CHERRY_BUTTON, Items.STICK),
            new WoodSet("mangrove", Items.MANGROVE_WOOD, Items.STRIPPED_MANGROVE_WOOD, Items.MANGROVE_LOG, Items.STRIPPED_MANGROVE_LOG, Items.MANGROVE_PLANKS, Items.MANGROVE_STAIRS, Items.MANGROVE_SLAB, Items.MANGROVE_SHELF, Items.MANGROVE_FENCE, Items.MANGROVE_FENCE_GATE, Items.MANGROVE_TRAPDOOR, Items.MANGROVE_DOOR, Items.MANGROVE_SIGN, Items.MANGROVE_BUTTON, Items.STICK),
            new WoodSet("bamboo", null, null, Items.BAMBOO_BLOCK, Items.STRIPPED_BAMBOO_BLOCK, Items.BAMBOO_PLANKS, Items.BAMBOO_STAIRS, Items.BAMBOO_SLAB, Items.BAMBOO_SHELF, Items.BAMBOO_FENCE, Items.BAMBOO_FENCE_GATE, Items.BAMBOO_TRAPDOOR, Items.BAMBOO_DOOR, Items.BAMBOO_SIGN, Items.BAMBOO_BUTTON, Items.STICK),
            new WoodSet("crimson", Items.CRIMSON_HYPHAE, Items.STRIPPED_CRIMSON_HYPHAE, Items.CRIMSON_STEM, Items.STRIPPED_CRIMSON_STEM, Items.CRIMSON_PLANKS, Items.CRIMSON_STAIRS, Items.CRIMSON_SLAB, Items.CRIMSON_SHELF, Items.CRIMSON_FENCE, Items.CRIMSON_FENCE_GATE, Items.CRIMSON_TRAPDOOR, Items.CRIMSON_DOOR, Items.CRIMSON_SIGN, Items.CRIMSON_BUTTON, Items.STICK),
            new WoodSet("warped", Items.WARPED_HYPHAE, Items.STRIPPED_WARPED_HYPHAE, Items.WARPED_STEM, Items.STRIPPED_WARPED_STEM, Items.WARPED_PLANKS, Items.WARPED_STAIRS, Items.WARPED_SLAB, Items.WARPED_SHELF, Items.WARPED_FENCE, Items.WARPED_FENCE_GATE, Items.WARPED_TRAPDOOR, Items.WARPED_DOOR, Items.WARPED_SIGN, Items.WARPED_BUTTON, Items.STICK),
            new WoodSet("poplar", Items.POPLAR_WOOD, Items.STRIPPED_POPLAR_WOOD, Items.POPLAR_LOG, Items.STRIPPED_POPLAR_LOG, Items.POPLAR_PLANKS, Items.POPLAR_STAIRS, Items.POPLAR_SLAB, Items.POPLAR_SHELF, Items.POPLAR_FENCE, Items.POPLAR_FENCE_GATE, Items.POPLAR_TRAPDOOR, Items.POPLAR_DOOR, Items.POPLAR_SIGN, Items.POPLAR_BUTTON, Items.STICK)
    );

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries,
                                                  BootstrapContext<Recipe<?>> recipeOutput,
                                                  BootstrapContext<Advancement> advancementOutput) {
        return new RecipeProvider(recipeOutput, advancementOutput) {

            private void woodcut(ItemLike input, ItemLike result, int count) {
                String name = getItemName(result) + "_from_" + getItemName(input) + "_woodcutting";
                ResourceKey<Recipe<?>> key = ResourceKey.create(Registries.RECIPE,
                        Identifier.fromNamespaceAndPath(Woodcutter.MOD_ID, name));

                WoodcutterRecipeBuilder
                        .woodcutting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, result, count)
                        .unlockedBy(getHasName(input), has(input))
                        .save(this.output, key);
            }

            @Override
            public void buildRecipes() {
                for (WoodSet w : WOODS) {
                    // Log(like) conversions
                    woodcut(w.log(), w.stripped_log(), 1);
                    if (!w.isBamboo()) {
                        woodcut(w.log(), w.wood(), 1);
                        woodcut(w.log(), w.stripped_wood(), 1);
                        woodcut(w.stripped_log(), w.stripped_wood(), 1);
                        woodcut(w.wood(), w.stripped_wood(), 1);
                        woodcut(w.wood(), w.log(), 1);
                        woodcut(w.wood(), w.stripped_log(), 1);
                        woodcut(w.stripped_wood(), w.stripped_log(), 1);
                    }

                    // Products (logs, planks, and salvage)
                    for (Product p : PRODUCTS) {
                        Item out = p.item().apply(w);
                        for (Item loglike : w.loglikes()) {
                            woodcut(loglike, out, p.fromLog());
                        }
                        if (p.fromPlanks() > 0) woodcut(w.planks(), out, p.fromPlanks());
                        if (p.salvage() > 0) woodcut(out, w.sticks(), p.salvage());
                    }

                    if (w.isBamboo()) buildMosaicRecipes(w);
                }
            }

            private void buildMosaicRecipes(WoodSet w) {
                for (Item loglike : w.loglikes()) {
                    woodcut(loglike, Items.BAMBOO_MOSAIC, 4);
                    woodcut(loglike, Items.BAMBOO_MOSAIC_STAIRS, 4);
                    woodcut(loglike, Items.BAMBOO_MOSAIC_SLAB, 8);
                }

                woodcut(w.planks(), Items.BAMBOO_MOSAIC, 1);
                woodcut(Items.BAMBOO_MOSAIC, w.planks(), 1);
                woodcut(Items.BAMBOO_MOSAIC, Items.BAMBOO_MOSAIC_STAIRS, 1);
                woodcut(Items.BAMBOO_MOSAIC, Items.BAMBOO_MOSAIC_SLAB, 2);
                woodcut(w.planks(), Items.BAMBOO_MOSAIC_STAIRS, 1);
                woodcut(w.planks(), Items.BAMBOO_MOSAIC_SLAB, 2);

                for (Product p : PRODUCTS) {
                    if (p.fromPlanks() > 0) {
                        woodcut(Items.BAMBOO_MOSAIC, p.item().apply(w), p.fromPlanks());
                    }
                }

                woodcut(Items.BAMBOO_MOSAIC_STAIRS, w.stairs(), 1);
                woodcut(Items.BAMBOO_MOSAIC_SLAB, w.slab(), 1);
                woodcut(w.stairs(), Items.BAMBOO_MOSAIC_STAIRS, 1);
                woodcut(w.slab(), Items.BAMBOO_MOSAIC_SLAB, 1);

                woodcut(Items.BAMBOO_MOSAIC_STAIRS, w.sticks(), 1);
                woodcut(Items.BAMBOO_MOSAIC_SLAB, w.sticks(), 1);
            }
        };
    }

    @Override
    public String getName() {
        return "Woodcutter Recipes";
    }
}
