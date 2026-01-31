package com.floopowder.fabric;

import com.floopowder.api.IFlooVersionHelper;

/**
 * Fabric 1.19.2 version helper.
 */
public class FabricVersionHelper implements IFlooVersionHelper {

  @Override
  public String getMinecraftVersion() {
    return "1.19.2";
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
    return false;
  }
}
