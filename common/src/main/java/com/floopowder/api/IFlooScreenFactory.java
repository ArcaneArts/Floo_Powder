package com.floopowder.api;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

/**
 * Factory for creating and registering screens from logic implementations.
 * Implemented by adapters for each Minecraft version/loader.
 */
public interface IFlooScreenFactory {

  /**
   * Creates a non-container screen from a logic implementation.
   *
   * @param title The screen title
   * @param logic The screen logic implementation
   * @return A version-specific Screen instance
   */
  Screen createScreen(Component title, IFlooScreenLogic logic);

  /**
   * Registers a container screen for a menu type.
   * Call this during client setup to associate a menu type with screen logic.
   *
   * @param <T>           The menu type
   * @param menuType      The menu type to register the screen for
   * @param logicSupplier Supplier that creates a new logic instance for each screen
   */
  <T extends AbstractContainerMenu> void registerContainerScreen(
      MenuType<T> menuType,
      Supplier<IFlooContainerScreenLogic<T>> logicSupplier
  );
}
