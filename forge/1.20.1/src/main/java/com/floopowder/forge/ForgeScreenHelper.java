package com.floopowder.forge;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

/**
 * Forge 1.20.1 screen helper.
 * Provides version-specific screen rendering methods.
 */
public final class ForgeScreenHelper {

  private ForgeScreenHelper() {
  }

  /**
   * Renders the screen background using 1.20.1 API (1 parameter).
   * The mouseX, mouseY, partialTick params are ignored in this version.
   */
  public static void renderBackground(Screen screen, GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
    screen.renderBackground(graphics);
  }

  /**
   * Gets vertical scroll delta from mouseScrolled parameters.
   * In 1.20.1, mouseScrolled has 3 params: mouseX, mouseY, delta
   * This helper expects the delta to be passed as scrollX.
   *
   * @param scrollDelta The scroll delta (only param in 1.20.1)
   * @param unused Unused in 1.20.1
   * @return The vertical scroll delta
   */
  public static double getVerticalScroll(double scrollDelta, double unused) {
    return scrollDelta;
  }
}
