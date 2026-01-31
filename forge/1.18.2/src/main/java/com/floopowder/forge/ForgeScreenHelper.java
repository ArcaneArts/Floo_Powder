package com.floopowder.forge;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;

/**
 * Forge 1.18.2 screen helper.
 * Uses PoseStack instead of GuiGraphics.
 */
public final class ForgeScreenHelper {

  private ForgeScreenHelper() {
  }

  /**
   * Renders the screen background using 1.18.2 API (PoseStack, 1 parameter).
   */
  public static void renderBackground(Screen screen, PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
    screen.renderBackground(poseStack);
  }

  /**
   * Gets vertical scroll delta.
   */
  public static double getVerticalScroll(double scrollDelta, double unused) {
    return scrollDelta;
  }
}
