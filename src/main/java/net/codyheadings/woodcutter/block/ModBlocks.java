package net.codyheadings.woodcutter.block;

import net.codyheadings.woodcutter.Woodcutter;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public class ModBlocks {
    public static final WoodcutterBlock WOODCUTTER = registerBlock("woodcutter", properties ->
            new WoodcutterBlock(properties.mapColor(Blocks.SPRUCE_PLANKS.defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)));

    private static <T extends Block> T registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        Identifier id = Identifier.fromNamespaceAndPath(Woodcutter.MOD_ID, name);
        T toRegister = function.apply(BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, id)));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, id, toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Identifier id = Identifier.fromNamespaceAndPath(Woodcutter.MOD_ID, name);
        BlockItem item = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                .setId(ResourceKey.create(Registries.ITEM, id)));
        item.registerBlocks(Item.BY_BLOCK, item);
        Registry.register(BuiltInRegistries.ITEM, id, item);
    }

    public static void registerModBlocks(){
        Woodcutter.LOGGER.info("Registering Mod Blocks for " + Woodcutter.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(output -> output.accept(WOODCUTTER));
    }
}
