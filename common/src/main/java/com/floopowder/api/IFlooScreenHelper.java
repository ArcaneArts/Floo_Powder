package com.floopowder.api;

/**
 * Screen rendering helpers that abstract version-specific APIs.
 * Platform implementations handle the actual rendering calls.
 */
public interface IFlooScreenHelper {

  /**
   * Renders the screen background using the appropriate API for this version.
   *
   * @param screen The screen instance
   * @param graphics The graphics context (GuiGraphics or PoseStack depending on version)
   * @param mouseX Mouse X position
   * @param mouseY Mouse Y position
   * @param partialTick Partial tick time
   */
  void renderScreenBackground(Object screen, Object graphics, int mouseX, int mouseY, float partialTick);

  /**
   * Gets the effective vertical scroll delta from mouseScrolled parameters.
   * 1.20.1: mouseScrolled(mouseX, mouseY, scrollDelta) - delta is param1
   * 1.20.2: mouseScrolled(mouseX, mouseY, scrollX, scrollY) - scrollY is param2
   *
   * @param param1 First scroll parameter (delta on 1.20.1, scrollX on 1.20.2)
   * @param param2 Second scroll parameter (unused on 1.20.1, scrollY on 1.20.2)
   * @return The vertical scroll amount
   */
  double getVerticalScroll(double param1, double param2);
}
