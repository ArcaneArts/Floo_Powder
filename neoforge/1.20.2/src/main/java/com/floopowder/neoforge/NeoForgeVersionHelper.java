package com.floopowder.neoforge;

import com.floopowder.api.IFlooVersionHelper;

/**
 * NeoForge 1.20.2 version helper.
 */
public class NeoForgeVersionHelper implements IFlooVersionHelper {

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
    return scrollYOrUnused;
  }

  @Override
  public boolean usesGuiGraphics() {
    return true;
  }
}
