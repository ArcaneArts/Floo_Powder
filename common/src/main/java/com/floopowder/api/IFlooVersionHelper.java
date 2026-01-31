package com.floopowder.api;

/**
 * Version-specific API abstractions for Minecraft.
 * Each Minecraft version provides an implementation that handles
 * API differences between versions.
 */
public interface IFlooVersionHelper {

  /**
   * Returns the Minecraft version string (e.g., "1.20.2").
   */
  String getMinecraftVersion();

  /**
   * Returns true if this version uses the new 4-parameter renderBackground API.
   * 1.20.1 and earlier: renderBackground(GuiGraphics)
   * 1.20.2 and later: renderBackground(GuiGraphics, mouseX, mouseY, partialTick)
   */
  boolean usesNewRenderBackgroundAPI();

  /**
   * Returns true if this version uses the new 4-parameter mouseScrolled API.
   * 1.20.1 and earlier: mouseScrolled(mouseX, mouseY, scrollDelta)
   * 1.20.2 and later: mouseScrolled(mouseX, mouseY, scrollX, scrollY)
   */
  boolean usesNewScrollAPI();

  /**
   * Gets the effective vertical scroll delta from scroll parameters.
   * On 1.20.1: returns scrollDelta directly
   * On 1.20.2: returns scrollY (vertical component)
   */
  double getVerticalScrollDelta(double scrollXOrDelta, double scrollYOrUnused);

  /**
   * Returns true if this version uses GuiGraphics instead of PoseStack.
   * 1.19.x and earlier: PoseStack
   * 1.20.x and later: GuiGraphics
   */
  boolean usesGuiGraphics();
}
