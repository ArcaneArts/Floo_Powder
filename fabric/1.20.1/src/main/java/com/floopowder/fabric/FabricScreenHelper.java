package com.floopowder.fabric;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

/**
 * Fabric 1.20.1 screen helper.
 */
public final class FabricScreenHelper {

  private FabricScreenHelper() {
  }

  public static void renderBackground(Screen screen, GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
    screen.renderBackground(graphics);
  }

  public static double getVerticalScroll(double scrollDelta, double unused) {
    return scrollDelta;
  }
}
