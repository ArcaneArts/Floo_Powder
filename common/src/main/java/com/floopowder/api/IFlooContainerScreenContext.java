package com.floopowder.api;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;

/**
 * Extends IFlooScreenContext with container-specific utilities.
 * Implemented by adapters for container screens.
 *
 * @param <T> The menu type
 */
public interface IFlooContainerScreenContext<T extends AbstractContainerMenu> extends IFlooScreenContext {

  /**
   * Gets the container menu.
   */
  T getMenu();

  /**
   * Gets the left position of the container GUI.
   */
  int getLeftPos();

  /**
   * Gets the top position of the container GUI.
   */
  int getTopPos();

  /**
   * Gets the image width of the container GUI.
   */
  int getImageWidth();

  /**
   * Gets the image height of the container GUI.
   */
  int getImageHeight();

  /**
   * Sets the image width of the container GUI.
   * Should be called during init before position calculations.
   *
   * @param width The new width
   */
  void setImageWidth(int width);

  /**
   * Sets the image height of the container GUI.
   * Should be called during init before position calculations.
   *
   * @param height The new height
   */
  void setImageHeight(int height);

  /**
   * Gets the slot currently under the mouse, if any.
   *
   * @return The slot under the mouse, or null if none
   */
  Slot getSlotUnderMouse();

  /**
   * Checks if the mouse is hovering over a slot.
   *
   * @param slot   The slot to check
   * @param mouseX Mouse X position
   * @param mouseY Mouse Y position
   * @return true if hovering over the slot
   */
  boolean isHovering(Slot slot, double mouseX, double mouseY);

  /**
   * Checks if a point is within a region relative to the GUI.
   *
   * @param x      Region X (relative to leftPos)
   * @param y      Region Y (relative to topPos)
   * @param width  Region width
   * @param height Region height
   * @param mouseX Mouse X position (absolute)
   * @param mouseY Mouse Y position (absolute)
   * @return true if the point is within the region
   */
  boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY);
}
