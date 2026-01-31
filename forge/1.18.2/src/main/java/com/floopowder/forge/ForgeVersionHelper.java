package com.floopowder.forge;

import com.floopowder.api.IFlooVersionHelper;

/**
 * Forge 1.18.2 version helper.
 */
public class ForgeVersionHelper implements IFlooVersionHelper {

  @Override
  public String getMinecraftVersion() {
    return "1.18.2";
  }

  @Override
  public boolean usesNewRenderBackgroundAPI() {
    return false;
  }

  @Override
  public boolean usesNewScrollAPI() {
    return false;
  }

  @Override
  public double getVerticalScrollDelta(double scrollXOrDelta, double scrollYOrUnused) {
    return scrollXOrDelta;
  }

  @Override
  public boolean usesGuiGraphics() {
    return false; // 1.18.2 uses PoseStack
  }
}
