package com.floopowder.fabric;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

/**
 * Fabric 1.20.2 screen helper.
 */
public final class FabricScreenHelper {

  private FabricScreenHelper() {
  }

  public static void renderBackground(Screen screen, GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
    screen.renderBackground(graphics, mouseX, mouseY, partialTick);
  }

  public static double getVerticalScroll(double scrollX, double scrollY) {
    return scrollY;
  }
}
