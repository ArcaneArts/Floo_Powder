package com.floopowder.graphics;

import com.floopowder.api.IFlooGraphics;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

/**
 * Fabric 1.20.1 implementation wrapping GuiGraphics.
 */
public class FlooGraphics implements IFlooGraphics {

  private final GuiGraphics graphics;

  public FlooGraphics(GuiGraphics graphics) {
    this.graphics = graphics;
  }

  @Override
  public void blit(ResourceLocation texture, int x, int y, int u, int v, int width, int height) {
    graphics.blit(texture, x, y, u, v, width, height);
  }

  @Override
  public void blit(ResourceLocation texture, int x, int y, int u, int v, int width, int height, int texWidth, int texHeight) {
    graphics.blit(texture, x, y, u, v, width, height, texWidth, texHeight);
  }

  @Override
  public void blit(ResourceLocation texture, int x, int y, float u, float v, int width, int height, int texWidth, int texHeight) {
    graphics.blit(texture, x, y, (int) u, (int) v, width, height, texWidth, texHeight);
  }

  @Override
  public void fill(int x1, int y1, int x2, int y2, int color) {
    graphics.fill(x1, y1, x2, y2, color);
  }

  @Override
  public void fillGradient(int x1, int y1, int x2, int y2, int colorTop, int colorBottom) {
    graphics.fillGradient(x1, y1, x2, y2, colorTop, colorBottom);
  }

  @Override
  public void drawString(Font font, String text, int x, int y, int color) {
    graphics.drawString(font, text, x, y, color, false);
  }

  @Override
  public void drawString(Font font, String text, int x, int y, int color, boolean shadow) {
    graphics.drawString(font, text, x, y, color, shadow);
  }

  @Override
  public void drawString(Font font, Component text, int x, int y, int color, boolean shadow) {
    graphics.drawString(font, text, x, y, color, shadow);
  }

  @Override
  public void drawCenteredString(Font font, String text, int x, int y, int color) {
    graphics.drawCenteredString(font, text, x, y, color);
  }

  @Override
  public void drawCenteredString(Font font, Component text, int x, int y, int color) {
    graphics.drawCenteredString(font, text, x, y, color);
  }

  @Override
  public void renderItem(ItemStack stack, int x, int y) {
    graphics.renderItem(stack, x, y);
  }

  @Override
  public void renderTooltip(Font font, List<Component> lines, int mouseX, int mouseY) {
    graphics.renderTooltip(font, lines, Optional.empty(), mouseX, mouseY);
  }

  @Override
  public void enableScissor(int x1, int y1, int x2, int y2) {
    graphics.enableScissor(x1, y1, x2, y2);
  }

  @Override
  public void disableScissor() {
    graphics.disableScissor();
  }

  @Override
  public void pushPose() {
    graphics.pose().pushPose();
  }

  @Override
  public void popPose() {
    graphics.pose().popPose();
  }

  @Override
  public void translate(double x, double y, double z) {
    graphics.pose().translate(x, y, z);
  }

  @Override
  public void scale(float x, float y, float z) {
    graphics.pose().scale(x, y, z);
  }

  @Override
  public Object getUnderlying() {
    return graphics;
  }
}
