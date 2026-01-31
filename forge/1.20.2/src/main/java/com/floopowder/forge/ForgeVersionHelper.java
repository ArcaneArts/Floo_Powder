package com.floopowder.forge;

import com.floopowder.api.IFlooVersionHelper;

/**
 * Forge 1.20.2 version helper.
 */
public class ForgeVersionHelper implements IFlooVersionHelper {

  @Override
  public String getMinecraftVersion() {
    return "1.20.2";
  }

  @Override
  public boolean usesNewRenderBackgroundAPI() {
    return true;
  }

  @Override
  public boolean usesNewScrollAPI() {
    return true;
  }

  @Override
  public double getVerticalScrollDelta(double scrollXOrDelta, double scrollYOrUnused) {
    // 1.20.2 uses separate scrollX and scrollY, return scrollY (second param)
    return scrollYOrUnused;
  }

  @Override
  public boolean usesGuiGraphics() {
    return true;
  }
}
