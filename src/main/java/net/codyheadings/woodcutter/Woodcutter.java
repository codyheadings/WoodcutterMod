package net.codyheadings.woodcutter;

import net.codyheadings.woodcutter.block.ModBlocks;
import net.codyheadings.woodcutter.recipe.WoodcutterRecipes;
import net.codyheadings.woodcutter.gui.ModMenuType;
import net.codyheadings.woodcutter.recipe.ModRecipeBookCategory;
import net.codyheadings.woodcutter.recipe.ModRecipeTypes;
import net.codyheadings.woodcutter.recipe.WoodcutterRecipesPayload;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Woodcutter implements ModInitializer {
	public static final String MOD_ID = "woodcutter";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Register blocks/recipes
		ModBlocks.registerModBlocks();
		ModMenuType.registerModMenuType();
		ModRecipeTypes.registerModRecipes();
		ModRecipeBookCategory.registerModRecipeBookCategory();

		// Register recipe payload to send to client
		PayloadTypeRegistry.clientboundPlay().register(
				WoodcutterRecipesPayload.TYPE, WoodcutterRecipesPayload.CODEC);

		// Build recipes when server starts
		ServerLifecycleEvents.SERVER_STARTED.register(server ->
				WoodcutterRecipes.setServer(WoodcutterRecipes.build(server)));

		// Send recipes to players on server
		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) -> {
			WoodcutterRecipes.setServer(WoodcutterRecipes.build(server));
			for (ServerPlayer player : server.getPlayerList().getPlayers()) {
				ServerPlayNetworking.send(player,
						new WoodcutterRecipesPayload(WoodcutterRecipes.get(player.level())));
			}
		});

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->
				ServerPlayNetworking.send(handler.getPlayer(),
						new WoodcutterRecipesPayload(WoodcutterRecipes.get(handler.getPlayer().level()))));
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
