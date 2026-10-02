package net.codyheadings.woodcutter.datagen;

import net.codyheadings.woodcutter.Woodcutter;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
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
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private record UnstrippedWoodSet(String name, Item log, Item stripped_log, Item planks, Item stairs, Item slab, Item fence, Item gate, Item trapdoor, Item door, Item sign, Item button, Item sticks) {}
//    private record StrippedWoodSet(String name, Item log, Item planks, Item stairs, Item slab, Item fence, Item gate, Item trapdoor, Item door, Item button, Item sign, Item sticks) {}

    private static final List<UnstrippedWoodSet> UNSTRIPPED_WOODS = List.of(
            new UnstrippedWoodSet("oak", Items.OAK_LOG, Items.STRIPPED_OAK_LOG, Items.OAK_PLANKS, Items.OAK_STAIRS, Items.OAK_SLAB, Items.OAK_FENCE, Items.OAK_FENCE_GATE, Items.OAK_TRAPDOOR, Items.OAK_DOOR, Items.OAK_SIGN, Items.OAK_BUTTON, Items.STICK),
            new UnstrippedWoodSet("spruce", Items.SPRUCE_LOG, Items.STRIPPED_SPRUCE_LOG, Items.SPRUCE_PLANKS, Items.SPRUCE_STAIRS, Items.SPRUCE_SLAB, Items.SPRUCE_FENCE, Items.SPRUCE_FENCE_GATE, Items.SPRUCE_TRAPDOOR, Items.SPRUCE_DOOR, Items.SPRUCE_SIGN, Items.SPRUCE_BUTTON, Items.STICK),
            new UnstrippedWoodSet("birch",Items.BIRCH_LOG,  Items.STRIPPED_BIRCH_LOG, Items.BIRCH_PLANKS, Items.BIRCH_STAIRS, Items.BIRCH_SLAB, Items.BIRCH_FENCE, Items.BIRCH_FENCE_GATE, Items.BIRCH_TRAPDOOR, Items.BIRCH_DOOR, Items.BIRCH_SIGN, Items.BIRCH_BUTTON, Items.STICK),
            new UnstrippedWoodSet("jungle",Items.JUNGLE_LOG, Items.STRIPPED_JUNGLE_LOG, Items.JUNGLE_PLANKS, Items.JUNGLE_STAIRS, Items.JUNGLE_SLAB, Items.JUNGLE_FENCE, Items.JUNGLE_FENCE_GATE, Items.JUNGLE_TRAPDOOR, Items.JUNGLE_DOOR, Items.JUNGLE_SIGN, Items.JUNGLE_BUTTON, Items.STICK),
            new UnstrippedWoodSet("dark_oak",Items.DARK_OAK_LOG, Items.STRIPPED_DARK_OAK_LOG, Items.DARK_OAK_PLANKS, Items.DARK_OAK_STAIRS, Items.DARK_OAK_SLAB, Items.DARK_OAK_FENCE, Items.DARK_OAK_FENCE_GATE, Items.DARK_OAK_TRAPDOOR, Items.DARK_OAK_DOOR, Items.DARK_OAK_SIGN, Items.DARK_OAK_BUTTON, Items.STICK),
            new UnstrippedWoodSet("acacia", Items.ACACIA_LOG, Items.STRIPPED_ACACIA_LOG, Items.ACACIA_PLANKS, Items.ACACIA_STAIRS, Items.ACACIA_SLAB, Items.ACACIA_FENCE, Items.ACACIA_FENCE_GATE, Items.ACACIA_TRAPDOOR, Items.ACACIA_DOOR, Items.ACACIA_SIGN, Items.ACACIA_BUTTON, Items.STICK),
            new UnstrippedWoodSet("pale_oak", Items.PALE_OAK_LOG, Items.STRIPPED_PALE_OAK_LOG, Items.PALE_OAK_PLANKS, Items.PALE_OAK_STAIRS, Items.PALE_OAK_SLAB, Items.PALE_OAK_FENCE, Items.PALE_OAK_FENCE_GATE, Items.PALE_OAK_TRAPDOOR, Items.PALE_OAK_DOOR, Items.PALE_OAK_SIGN, Items.PALE_OAK_BUTTON, Items.STICK),
            new UnstrippedWoodSet("cherry", Items.CHERRY_LOG, Items.STRIPPED_CHERRY_LOG, Items.CHERRY_PLANKS, Items.CHERRY_STAIRS, Items.CHERRY_SLAB, Items.CHERRY_FENCE, Items.CHERRY_FENCE_GATE, Items.CHERRY_TRAPDOOR, Items.CHERRY_DOOR, Items.CHERRY_SIGN, Items.CHERRY_BUTTON, Items.STICK),
            new UnstrippedWoodSet("mangrove", Items.MANGROVE_LOG, Items.STRIPPED_MANGROVE_LOG, Items.MANGROVE_PLANKS, Items.MANGROVE_STAIRS, Items.MANGROVE_SLAB, Items.MANGROVE_FENCE, Items.MANGROVE_FENCE_GATE, Items.MANGROVE_TRAPDOOR, Items.MANGROVE_DOOR, Items.MANGROVE_SIGN, Items.MANGROVE_BUTTON, Items.STICK),
            new UnstrippedWoodSet("bamboo", Items.BAMBOO_BLOCK, Items.STRIPPED_BAMBOO_BLOCK, Items.BAMBOO_PLANKS, Items.BAMBOO_STAIRS, Items.BAMBOO_SLAB, Items.BAMBOO_FENCE, Items.BAMBOO_FENCE_GATE, Items.BAMBOO_TRAPDOOR, Items.BAMBOO_DOOR, Items.BAMBOO_SIGN, Items.BAMBOO_BUTTON, Items.STICK),
            new UnstrippedWoodSet("crimson", Items.CRIMSON_STEM, Items.STRIPPED_CRIMSON_STEM, Items.CRIMSON_PLANKS, Items.CRIMSON_STAIRS, Items.CRIMSON_SLAB, Items.CRIMSON_FENCE, Items.CRIMSON_FENCE_GATE, Items.CRIMSON_TRAPDOOR, Items.CRIMSON_DOOR, Items.CRIMSON_SIGN, Items.CRIMSON_BUTTON, Items.STICK),
            new UnstrippedWoodSet("warped", Items.WARPED_STEM, Items.STRIPPED_WARPED_STEM, Items.WARPED_PLANKS, Items.WARPED_STAIRS, Items.WARPED_SLAB, Items.WARPED_FENCE, Items.WARPED_FENCE_GATE, Items.WARPED_TRAPDOOR, Items.WARPED_DOOR, Items.WARPED_SIGN, Items.WARPED_BUTTON, Items.STICK)
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
                for (UnstrippedWoodSet w : UNSTRIPPED_WOODS) {
                    // Logs
                    woodcut(w.log(), w.stripped_log(), 1);

                    for (ItemLike logInput : List.of(w.log(), w.stripped_log())) {
                        woodcut(logInput, w.planks(), 4);
                        woodcut(logInput, w.stairs(), 4);
                        woodcut(logInput, w.slab(), 8);
                        woodcut(logInput, w.fence(), 4);
                        woodcut(logInput, w.gate(), 4);
                        woodcut(logInput, w.door(), 4);
                        woodcut(logInput, w.trapdoor(), 12);
                        woodcut(logInput, w.sign(), 4);
                        woodcut(logInput, w.button(), 16);
                        woodcut(logInput, w.sticks(), 12);
                    }

                    // Planks
                    woodcut(w.planks(), w.stairs(), 1);
                    woodcut(w.planks(), w.slab(), 2);
                    woodcut(w.planks(), w.fence(), 1);
                    woodcut(w.planks(), w.gate(), 1);
                    woodcut(w.planks(), w.door(), 1);
                    woodcut(w.planks(), w.trapdoor(), 3);
                    woodcut(w.planks(), w.sign(), 1);
                    woodcut(w.planks(), w.button(), 4);
                    woodcut(w.planks(), w.sticks(), 3);

                    // Salvage
                    woodcut(w.stairs(), w.sticks(), 1);
                    woodcut(w.slab(), w.sticks(), 1);
                    woodcut(w.fence(), w.sticks(), 1);
                    woodcut(w.gate(), w.sticks(), 1);
                    woodcut(w.door(), w.sticks(), 1);
                    woodcut(w.trapdoor(), w.sticks(), 1);
                    woodcut(w.sign(), w.sticks(), 1);
                }
            }
        };
    }

    @Override
    public String getName() {
        return "Woodcutter Recipes";
    }
}
