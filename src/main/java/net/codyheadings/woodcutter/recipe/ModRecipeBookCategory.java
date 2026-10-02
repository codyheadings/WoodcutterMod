package net.codyheadings.woodcutter.recipe;

import net.codyheadings.woodcutter.Woodcutter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeBookCategory;

public class ModRecipeBookCategory {
    public static final RecipeBookCategory WOODCUTTER = register("woodcutter");

    private static RecipeBookCategory register(final String id) {
        return Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY, Identifier.fromNamespaceAndPath(Woodcutter.MOD_ID, id), new RecipeBookCategory());
    }

    public static void registerModRecipeBookCategory(){
        Woodcutter.LOGGER.info("Registering Mod Recipe Book Category for " + Woodcutter.MOD_ID);
    }
}
