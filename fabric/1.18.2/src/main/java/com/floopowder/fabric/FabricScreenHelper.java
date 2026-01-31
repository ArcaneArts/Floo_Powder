package com.floopowder.fabric;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;

/**
 * Fabric 1.18.2 screen helper.
 */
public final class FabricScreenHelper {

  private FabricScreenHelper() {
  }

  public static void renderBackground(Screen screen, PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
    screen.renderBackground(poseStack);
  }

  public static double getVerticalScroll(double scrollDelta, double unused) {
    return scrollDelta;
  }
}
