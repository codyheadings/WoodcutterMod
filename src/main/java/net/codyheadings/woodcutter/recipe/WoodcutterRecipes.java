package net.codyheadings.woodcutter.recipe;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SelectableRecipe;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class WoodcutterRecipes {
    private static SelectableRecipe.SingleInputSet<WoodcutterRecipe> server = SelectableRecipe.SingleInputSet.empty();
    private static SelectableRecipe.SingleInputSet<WoodcutterRecipe> client = SelectableRecipe.SingleInputSet.empty();

    public static SelectableRecipe.SingleInputSet<WoodcutterRecipe> get(Level level) {
        return level.isClientSide() ? client : server;
    }

    public static void setServer(SelectableRecipe.SingleInputSet<WoodcutterRecipe> set) { server = set; }
    public static void setClient(SelectableRecipe.SingleInputSet<WoodcutterRecipe> set) { client = set; }

    public static SelectableRecipe.SingleInputSet<WoodcutterRecipe> build(MinecraftServer server) {
        FeatureFlagSet flags = server.getWorldData().enabledFeatures();
        List<SelectableRecipe.SingleInputEntry<WoodcutterRecipe>> entries = new ArrayList<>();
        for (RecipeHolder<?> holder : server.getRecipeManager().getRecipes()) {
            if (holder.value() instanceof WoodcutterRecipe r
                    && r.input().items().allMatch(i -> i.value().isEnabled(flags))
                    && r.resultDisplay().isEnabled(flags)) {
                entries.add(new SelectableRecipe.SingleInputEntry<>(
                        r.input(),
                        new SelectableRecipe<>(r.resultDisplay(),
                                Optional.of((RecipeHolder<WoodcutterRecipe>) holder))));
            }
        }
        return new SelectableRecipe.SingleInputSet<>(entries);
    }
}