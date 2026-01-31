package com.floopowder.forge;

import com.floopowder.api.IFlooVersionHelper;

/**
 * Forge 1.20.1 version helper.
 */
public class ForgeVersionHelper implements IFlooVersionHelper {

  @Override
  public String getMinecraftVersion() {
    return "1.20.1";
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
    // 1.20.1 uses single scrollDelta (first param)
    return scrollXOrDelta;
  }

  @Override
  public boolean usesGuiGraphics() {
    return true;
  }
}
