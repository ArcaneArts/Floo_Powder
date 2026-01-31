package com.floopowder.fabric;

import com.floopowder.api.IFlooScreenHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;

public class FabricFlooScreenHelper implements IFlooScreenHelper {

  @Override
  public void renderScreenBackground(Object screen, Object graphics, int mouseX, int mouseY, float partialTick) {
    ((Screen) screen).renderBackground((PoseStack) graphics);
  }

  @Override
  public double getVerticalScroll(double param1, double param2) {
    return param1;
  }
}
