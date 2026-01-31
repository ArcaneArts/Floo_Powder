package com.floopowder.api;

import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * Version-agnostic graphics abstraction for Minecraft GUI rendering.
 * Wraps GuiGraphics (1.20.x) or PoseStack+RenderSystem (1.18.x/1.19.x).
 */
public interface IFlooGraphics {

  /**
   * Draws a textured rectangle.
   */
  void blit(ResourceLocation texture, int x, int y, int u, int v, int width, int height);

  /**
   * Draws a textured rectangle with custom texture dimensions.
   */
  void blit(ResourceLocation texture, int x, int y, int u, int v, int width, int height, int texWidth, int texHeight);

  /**
   * Draws a textured rectangle with float UV coordinates.
   */
  void blit(ResourceLocation texture, int x, int y, float u, float v, int width, int height, int texWidth, int texHeight);

  /**
   * Fills a rectangle with a solid color.
   */
  void fill(int x1, int y1, int x2, int y2, int color);

  /**
   * Fills a rectangle with a vertical gradient.
   */
  void fillGradient(int x1, int y1, int x2, int y2, int colorTop, int colorBottom);

  /**
   * Draws a string.
   */
  void drawString(Font font, String text, int x, int y, int color);

  /**
   * Draws a string with optional shadow.
   */
  void drawString(Font font, String text, int x, int y, int color, boolean shadow);

  /**
   * Draws a Component.
   */
  void drawString(Font font, Component text, int x, int y, int color, boolean shadow);

  /**
   * Draws a centered string.
   */
  void drawCenteredString(Font font, String text, int x, int y, int color);

  /**
   * Draws a centered Component.
   */
  void drawCenteredString(Font font, Component text, int x, int y, int color);

  /**
   * Renders an item stack.
   */
  void renderItem(ItemStack stack, int x, int y);

  /**
   * Renders a tooltip.
   */
  void renderTooltip(Font font, List<Component> lines, int mouseX, int mouseY);

  /**
   * Enables scissor/clipping region.
   */
  void enableScissor(int x1, int y1, int x2, int y2);

  /**
   * Disables scissor/clipping.
   */
  void disableScissor();

  /**
   * Push a pose/matrix onto the stack.
   */
  void pushPose();

  /**
   * Pop a pose/matrix from the stack.
   */
  void popPose();

  /**
   * Translate the current pose.
   */
  void translate(double x, double y, double z);

  /**
   * Scale the current pose.
   */
  void scale(float x, float y, float z);

  /**
   * Gets the underlying graphics object (GuiGraphics or PoseStack).
   * Use sparingly - prefer the abstracted methods.
   */
  Object getUnderlying();
}
