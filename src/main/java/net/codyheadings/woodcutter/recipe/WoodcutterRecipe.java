package net.codyheadings.woodcutter.recipe;

import com.mojang.serialization.MapCodec;
import net.codyheadings.woodcutter.block.ModBlocks;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

import java.util.List;

public class WoodcutterRecipe extends SingleItemRecipe {
    public static final MapCodec<WoodcutterRecipe> MAP_CODEC = simpleMapCodec(WoodcutterRecipe::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, WoodcutterRecipe> STREAM_CODEC = simpleStreamCodec(WoodcutterRecipe::new);
    public static final RecipeSerializer<WoodcutterRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    public WoodcutterRecipe(final Recipe.CommonInfo commonInfo, final Ingredient ingredient, final ItemStackTemplate result) {
        super(commonInfo, ingredient, result);
    }

    @Override
    public RecipeSerializer<? extends SingleItemRecipe> getSerializer() {
        return ModRecipeTypes.WOODCUTTING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends SingleItemRecipe> getType() {
        return ModRecipeTypes.WOODCUTTING;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(new WoodcutterRecipeDisplay(this.input().display(), this.resultDisplay(), new SlotDisplay.ItemSlotDisplay(ModBlocks.WOODCUTTER.asItem())));
    }

    public SlotDisplay resultDisplay() {
        return new SlotDisplay.ItemStackSlotDisplay(this.result());
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return ModRecipeBookCategory.WOODCUTTER;
    }
}
