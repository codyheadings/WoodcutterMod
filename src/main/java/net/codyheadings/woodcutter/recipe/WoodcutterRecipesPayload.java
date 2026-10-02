package net.codyheadings.woodcutter.recipe;

import net.codyheadings.woodcutter.Woodcutter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.SelectableRecipe;

public record WoodcutterRecipesPayload(SelectableRecipe.SingleInputSet<WoodcutterRecipe> recipes)
        implements CustomPacketPayload {

    public static final Type<WoodcutterRecipesPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(Woodcutter.MOD_ID, "recipes"));

    public static final StreamCodec<RegistryFriendlyByteBuf, WoodcutterRecipesPayload> CODEC =
            StreamCodec.composite(
                    SelectableRecipe.SingleInputSet.noRecipeCodec(),
                    WoodcutterRecipesPayload::recipes,
                    WoodcutterRecipesPayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}