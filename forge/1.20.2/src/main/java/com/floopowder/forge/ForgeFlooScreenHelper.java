package com.floopowder.forge;

import com.floopowder.api.IFlooScreenHelper;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

public class ForgeFlooScreenHelper implements IFlooScreenHelper {

  @Override
  public void renderScreenBackground(Object screen, Object graphics, int mouseX, int mouseY, float partialTick) {
    ((Screen) screen).renderBackground((GuiGraphics) graphics, mouseX, mouseY, partialTick);
  }

  @Override
  public double getVerticalScroll(double param1, double param2) {
    return param2;
  }
}
