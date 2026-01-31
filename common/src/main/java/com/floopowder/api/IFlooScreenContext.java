package com.floopowder.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

/**
 * Provides access to screen utilities without exposing version-specific types.
 * Implemented by adapters for each Minecraft version.
 */
public interface IFlooScreenContext {

  /**
   * Gets the screen width.
   */
  int getWidth();

  /**
   * Gets the screen height.
   */
  int getHeight();

  /**
   * Gets the font renderer.
   */
  Font getFont();

  /**
   * Gets the Minecraft instance.
   */
  Minecraft getMinecraft();

  /**
   * Gets the screen title.
   */
  Component getTitle();

  /**
   * Adds a widget that can be rendered and interacted with.
   *
   * @param widget The widget to add (Button, EditBox, etc.)
   */
  void addRenderableWidget(Object widget);

  /**
   * Adds a renderable-only element (no interaction).
   *
   * @param renderable The renderable to add
   */
  void addRenderableOnly(Object renderable);

  /**
   * Removes a widget from the screen.
   *
   * @param widget The widget to remove
   */
  void removeWidget(Object widget);

  /**
   * Clears all widgets from the screen.
   */
  void clearWidgets();

  /**
   * Creates a version-agnostic graphics wrapper from a version-specific graphics object.
   *
   * @param poseStackOrGuiGraphics PoseStack (1.18/1.19) or GuiGraphics (1.20+)
   * @return A version-agnostic graphics wrapper
   */
  IFlooGraphics createGraphics(Object poseStackOrGuiGraphics);
}
