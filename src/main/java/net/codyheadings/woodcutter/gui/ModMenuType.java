package net.codyheadings.woodcutter.gui;

import net.codyheadings.woodcutter.Woodcutter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class ModMenuType {
    public static final MenuType<WoodcutterMenu> WOODCUTTER = register("woodcutter", WoodcutterMenu::new);

    private static <T extends AbstractContainerMenu> MenuType<T> register(final String name, final MenuType.MenuSupplier<T> constructor) {
        return Registry.register(
                BuiltInRegistries.MENU,
                Identifier.fromNamespaceAndPath(Woodcutter.MOD_ID, name),
                new MenuType<>(constructor, FeatureFlags.VANILLA_SET));
    }

    public static void registerModMenuType(){
        Woodcutter.LOGGER.info("Registering Mod Menu Types for " + Woodcutter.MOD_ID);
    }
}
