package net.codyheadings.woodcutter.recipe;

import net.codyheadings.woodcutter.Woodcutter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;

public class ModRecipeTypes {
    public static final RecipeSerializer<WoodcutterRecipe> WOODCUTTING_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Identifier.fromNamespaceAndPath(Woodcutter.MOD_ID, "woodcutting"),
            new RecipeSerializer<>(
                    SingleItemRecipe.simpleMapCodec(WoodcutterRecipe::new),
                    SingleItemRecipe.simpleStreamCodec(WoodcutterRecipe::new)));

    public static final RecipeType<WoodcutterRecipe> WOODCUTTING = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            Identifier.fromNamespaceAndPath(Woodcutter.MOD_ID, "woodcutting"),
            new RecipeType<>() {
                @Override
                public String toString() { return "woodcutting"; }
            });

    public static final RecipeDisplay.Type<WoodcutterRecipeDisplay> WOODCUTTER_DISPLAY_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_DISPLAY,
            Identifier.fromNamespaceAndPath(Woodcutter.MOD_ID, "woodcutter"),
            WoodcutterRecipeDisplay.TYPE);

    public static void registerModRecipes() {
        Woodcutter.LOGGER.info("Registering Mod Recipes for " + Woodcutter.MOD_ID);
    }
}
