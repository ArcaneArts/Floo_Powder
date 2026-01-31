package com.floopowder.screen;

import com.floopowder.api.IFlooContainerScreenLogic;
import com.floopowder.api.IFlooScreenFactory;
import com.floopowder.api.IFlooScreenLogic;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

/**
 * NeoForge 1.20.2 implementation of IFlooScreenFactory.
 */
public class FlooScreenFactory implements IFlooScreenFactory {

  @Override
  public Screen createScreen(Component title, IFlooScreenLogic logic) {
    return new FlooLogicScreen(title, logic);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends AbstractContainerMenu> void registerContainerScreen(
      MenuType<T> menuType,
      Supplier<IFlooContainerScreenLogic<T>> logicSupplier
  ) {
    MenuScreens.<T, FlooLogicContainerScreen<T>>register(menuType, (menu, inventory, title) ->
        new FlooLogicContainerScreen<>(menu, inventory, title, logicSupplier.get())
    );
  }
}
