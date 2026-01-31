package com.floopowder.graphics;

import com.floopowder.api.IFlooGraphics;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * Forge 1.19.2 implementation using PoseStack and RenderSystem.
 * Note: Does not extend GuiComponent to avoid conflicts with static methods.
 */
public class FlooGraphics implements IFlooGraphics {

  private final PoseStack poseStack;
  private final Minecraft mc;

  public FlooGraphics(PoseStack poseStack) {
    this.poseStack = poseStack;
    this.mc = Minecraft.getInstance();
  }

  @Override
  public void blit(ResourceLocation texture, int x, int y, int u, int v, int width, int height) {
    blit(texture, x, y, u, v, width, height, 256, 256);
  }

  @Override
  public void blit(ResourceLocation texture, int x, int y, int u, int v, int width, int height, int texWidth, int texHeight) {
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    RenderSystem.setShaderTexture(0, texture);
    Matrix4f matrix = poseStack.last().pose();
    float minU = (float) u / texWidth;
    float maxU = (float) (u + width) / texWidth;
    float minV = (float) v / texHeight;
    float maxV = (float) (v + height) / texHeight;
    BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
    bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
    bufferBuilder.vertex(matrix, x, y + height, 0).uv(minU, maxV).endVertex();
    bufferBuilder.vertex(matrix, x + width, y + height, 0).uv(maxU, maxV).endVertex();
    bufferBuilder.vertex(matrix, x + width, y, 0).uv(maxU, minV).endVertex();
    bufferBuilder.vertex(matrix, x, y, 0).uv(minU, minV).endVertex();
    BufferBuilder.RenderedBuffer renderedBuffer = bufferBuilder.end();
    com.mojang.blaze3d.vertex.BufferUploader.drawWithShader(renderedBuffer);
  }

  @Override
  public void blit(ResourceLocation texture, int x, int y, float u, float v, int width, int height, int texWidth, int texHeight) {
    blit(texture, x, y, (int) u, (int) v, width, height, texWidth, texHeight);
  }

  @Override
  public void fill(int x1, int y1, int x2, int y2, int color) {
    GuiComponent.fill(poseStack, x1, y1, x2, y2, color);
  }

  @Override
  public void fillGradient(int x1, int y1, int x2, int y2, int colorTop, int colorBottom) {
    // GuiComponent.fillGradient is protected, use fill with approximation
    GuiComponent.fill(poseStack, x1, y1, x2, y2, colorTop);
  }

  @Override
  public void drawString(Font font, String text, int x, int y, int color) {
    font.draw(poseStack, text, x, y, color);
  }

  @Override
  public void drawString(Font font, String text, int x, int y, int color, boolean shadow) {
    if (shadow) {
      font.drawShadow(poseStack, text, x, y, color);
    } else {
      font.draw(poseStack, text, x, y, color);
    }
  }

  @Override
  public void drawString(Font font, Component text, int x, int y, int color, boolean shadow) {
    if (shadow) {
      font.drawShadow(poseStack, text, x, y, color);
    } else {
      font.draw(poseStack, text, x, y, color);
    }
  }

  @Override
  public void drawCenteredString(Font font, String text, int x, int y, int color) {
    GuiComponent.drawCenteredString(poseStack, font, text, x, y, color);
  }

  @Override
  public void drawCenteredString(Font font, Component text, int x, int y, int color) {
    GuiComponent.drawCenteredString(poseStack, font, text, x, y, color);
  }

  @Override
  public void renderItem(ItemStack stack, int x, int y) {
    ItemRenderer itemRenderer = mc.getItemRenderer();
    itemRenderer.renderAndDecorateItem(stack, x, y);
  }

  @Override
  public void renderTooltip(Font font, List<Component> lines, int mouseX, int mouseY) {
    mc.screen.renderTooltip(poseStack, lines, java.util.Optional.empty(), mouseX, mouseY);
  }

  @Override
  public void enableScissor(int x1, int y1, int x2, int y2) {
    double scale = mc.getWindow().getGuiScale();
    int screenHeight = mc.getWindow().getHeight();
    RenderSystem.enableScissor(
        (int) (x1 * scale),
        (int) (screenHeight - y2 * scale),
        (int) ((x2 - x1) * scale),
        (int) ((y2 - y1) * scale)
    );
  }

  @Override
  public void disableScissor() {
    RenderSystem.disableScissor();
  }

  @Override
  public void pushPose() {
    poseStack.pushPose();
  }

  @Override
  public void popPose() {
    poseStack.popPose();
  }

  @Override
  public void translate(double x, double y, double z) {
    poseStack.translate(x, y, z);
  }

  @Override
  public void scale(float x, float y, float z) {
    poseStack.scale(x, y, z);
  }

  @Override
  public Object getUnderlying() {
    return poseStack;
  }
}
