package com.floopowder.screen;

import com.floopowder.api.IFlooContainerScreenContext;
import com.floopowder.api.IFlooGraphics;
import com.floopowder.graphics.FlooGraphics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;

/**
 * NeoForge 1.20.2 implementation of IFlooContainerScreenContext.
 * Gets position/size values from FlooLogicContainerScreen which has access to protected fields.
 */
public class FlooContainerScreenContext<T extends AbstractContainerMenu> implements IFlooContainerScreenContext<T> {

  private final FlooLogicContainerScreen<T> screen;
  private final T menu;

  public FlooContainerScreenContext(FlooLogicContainerScreen<T> screen, T menu) {
    this.screen = screen;
    this.menu = menu;
  }

  @Override
  public T getMenu() {
    return menu;
  }

  @Override
  public int getLeftPos() {
    return screen.getLeftPos();
  }

  @Override
  public int getTopPos() {
    return screen.getTopPos();
  }

  @Override
  public int getImageWidth() {
    return screen.getImageWidth();
  }

  @Override
  public int getImageHeight() {
    return screen.getImageHeight();
  }

  @Override
  public void setImageWidth(int width) {
    screen.setImageWidth(width);
  }

  @Override
  public void setImageHeight(int height) {
    screen.setImageHeight(height);
  }

  @Override
  public Slot getSlotUnderMouse() {
    return screen.getHoveredSlot();
  }

  @Override
  public boolean isHovering(Slot slot, double mouseX, double mouseY) {
    return isHovering(slot.x, slot.y, 16, 16, mouseX, mouseY);
  }

  @Override
  public boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY) {
    int left = getLeftPos();
    int top = getTopPos();
    mouseX -= left;
    mouseY -= top;
    return mouseX >= x - 1 && mouseX < x + width + 1 && mouseY >= y - 1 && mouseY < y + height + 1;
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
    if (widget instanceof GuiEventListener && widget instanceof Renderable && widget instanceof NarratableEntry) {
      screen.addRenderableWidget((GuiEventListener & Renderable & NarratableEntry) widget);
    }
  }

  @Override
  public void addRenderableOnly(Object renderable) {
    if (renderable instanceof Renderable) {
      screen.addRenderableOnly((Renderable) renderable);
    }
  }

  @Override
  public void removeWidget(Object widget) {
    if (widget instanceof GuiEventListener) {
      screen.removeWidget((GuiEventListener) widget);
    }
  }

  @Override
  public void clearWidgets() {
    screen.clearWidgets();
  }

  @Override
  public IFlooGraphics createGraphics(Object poseStackOrGuiGraphics) {
    if (poseStackOrGuiGraphics instanceof GuiGraphics guiGraphics) {
      return new FlooGraphics(guiGraphics);
    }
    throw new IllegalArgumentException("Expected GuiGraphics, got " + poseStackOrGuiGraphics.getClass().getName());
  }
}
