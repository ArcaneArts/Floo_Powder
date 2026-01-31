package com.floopowder.screen;

import com.floopowder.api.IFlooGraphics;
import com.floopowder.api.IFlooScreenContext;
import com.floopowder.graphics.FlooGraphics;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;

/**
 * Fabric 1.19.2 implementation of IFlooScreenContext.
 */
public class FlooScreenContext implements IFlooScreenContext {

  private final FlooLogicScreen screen;

  public FlooScreenContext(FlooLogicScreen screen) {
    this.screen = screen;
  }

  @Override
  public int getWidth() {
    return screen.width;
  }

  @Override
  public int getHeight() {
    return screen.height;
  }

  @Override
  public Font getFont() {
    return Minecraft.getInstance().font;
  }

  @Override
  public Minecraft getMinecraft() {
    return Minecraft.getInstance();
  }

  @Override
  public Component getTitle() {
    return screen.getTitle();
  }

  @Override
  public void addRenderableWidget(Object widget) {
    if (widget instanceof GuiEventListener && widget instanceof Widget && widget instanceof NarratableEntry) {
      screen.addRenderableWidgetPublic((GuiEventListener & Widget & NarratableEntry) widget);
    }
  }

  @Override
  public void addRenderableOnly(Object renderable) {
    if (renderable instanceof Widget) {
      screen.addRenderableOnlyPublic((Widget) renderable);
    }
  }

  @Override
  public void removeWidget(Object widget) {
    if (widget instanceof GuiEventListener) {
      screen.removeWidgetPublic((GuiEventListener) widget);
    }
  }

  @Override
  public void clearWidgets() {
    screen.clearWidgetsPublic();
  }

  @Override
  public IFlooGraphics createGraphics(Object poseStackOrGuiGraphics) {
    if (poseStackOrGuiGraphics instanceof PoseStack poseStack) {
      return new FlooGraphics(poseStack);
    }
    throw new IllegalArgumentException("Expected PoseStack, got " + poseStackOrGuiGraphics.getClass().getName());
  }
}
