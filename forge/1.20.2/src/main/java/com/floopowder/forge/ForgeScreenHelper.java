package com.floopowder.forge;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

/**
 * Forge 1.20.2 screen helper.
 * Provides version-specific screen rendering methods.
 */
public final class ForgeScreenHelper {

  private ForgeScreenHelper() {
  }

  /**
   * Renders the screen background using 1.20.2 API (4 parameters).
   */
  public static void renderBackground(Screen screen, GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
    screen.renderBackground(graphics, mouseX, mouseY, partialTick);
  }

  /**
   * Gets vertical scroll delta from mouseScrolled parameters.
   * In 1.20.2, mouseScrolled has 4 params: mouseX, mouseY, scrollX, scrollY
   *
   * @param scrollX Horizontal scroll (first scroll param)
   * @param scrollY Vertical scroll (second scroll param)
   * @return The vertical scroll delta
   */
  public static double getVerticalScroll(double scrollX, double scrollY) {
    return scrollY;
  }
}
