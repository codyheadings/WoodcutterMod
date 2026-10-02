package net.codyheadings.woodcutter;

import net.codyheadings.woodcutter.recipe.WoodcutterRecipes;
import net.codyheadings.woodcutter.block.WoodcutterScreen;
import net.codyheadings.woodcutter.gui.ModMenuType;
import net.codyheadings.woodcutter.recipe.WoodcutterRecipesPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.crafting.SelectableRecipe;

public class WoodcutterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuType.WOODCUTTER, WoodcutterScreen::new);

        ClientPlayNetworking.registerGlobalReceiver(WoodcutterRecipesPayload.TYPE,
                (payload, context) -> context.client().execute(() ->
                        WoodcutterRecipes.setClient(payload.recipes())));

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) ->
                WoodcutterRecipes.setClient(SelectableRecipe.SingleInputSet.empty()));
    }
}