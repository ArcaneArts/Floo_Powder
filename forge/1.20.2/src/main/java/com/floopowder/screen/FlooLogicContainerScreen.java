package com.floopowder.screen;

import com.floopowder.api.IFlooContainerScreenContext;
import com.floopowder.api.IFlooContainerScreenLogic;
import com.floopowder.api.IFlooGraphics;
import com.floopowder.graphics.FlooGraphics;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.NotNull;

/**
 * Forge 1.20.2 container screen that delegates to IFlooContainerScreenLogic.
 */
public class FlooLogicContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

  private final IFlooContainerScreenLogic<T> logic;
  private final IFlooContainerScreenContext<T> context;

  public FlooLogicContainerScreen(T menu, Inventory playerInventory, Component title, IFlooContainerScreenLogic<T> logic) {
    super(menu, playerInventory, title);
    this.logic = logic;
    this.context = new FlooContainerScreenContext<>(this, menu);
  }

  // Accessor methods for FlooContainerScreenContext
  public int getLeftPos() {
    return this.leftPos;
  }

  public int getTopPos() {
    return this.topPos;
  }

  public int getImageWidth() {
    return this.imageWidth;
  }

  public int getImageHeight() {
    return this.imageHeight;
  }

  public void setImageWidth(int width) {
    this.imageWidth = width;
  }

  public void setImageHeight(int height) {
    this.imageHeight = height;
  }

  public Slot getHoveredSlot() {
    return this.hoveredSlot;
  }

  // Widget management methods exposed for FlooContainerScreenContext
  public <W extends GuiEventListener & Renderable & NarratableEntry> W addRenderableWidget(W widget) {
    return super.addRenderableWidget(widget);
  }

  public <W extends Renderable> W addRenderableOnly(W widget) {
    return super.addRenderableOnly(widget);
  }

  public void removeWidget(GuiEventListener listener) {
    super.removeWidget(listener);
  }

  public void clearWidgets() {
    super.clearWidgets();
  }

  @Override
  protected void init() {
    logic.init(context);
    super.init();
  }

  @Override
  public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
    this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
    super.render(guiGraphics, mouseX, mouseY, partialTick);

    IFlooGraphics graphics = new FlooGraphics(guiGraphics);
    logic.renderTooltip(graphics, mouseX, mouseY);

    this.renderTooltip(guiGraphics, mouseX, mouseY);
  }

  @Override
  protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
    IFlooGraphics graphics = new FlooGraphics(guiGraphics);
    logic.renderBg(graphics, partialTick, mouseX, mouseY);
  }

  @Override
  protected void renderLabels(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
    IFlooGraphics graphics = new FlooGraphics(guiGraphics);
    logic.renderLabels(graphics, mouseX, mouseY);
  }

  @Override
  public boolean mouseClicked(double mouseX, double mouseY, int button) {
    if (logic.mouseClicked(mouseX, mouseY, button)) {
      return true;
    }
    return super.mouseClicked(mouseX, mouseY, button);
  }

  @Override
  public boolean mouseReleased(double mouseX, double mouseY, int button) {
    if (logic.mouseReleased(mouseX, mouseY, button)) {
      return true;
    }
    return super.mouseReleased(mouseX, mouseY, button);
  }

  @Override
  public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
    if (logic.mouseDragged(mouseX, mouseY, button, dragX, dragY)) {
      return true;
    }
    return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
  }

  @Override
  public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
    // Forge 1.20.2 uses 4-param mouseScrolled with scrollX and scrollY
    if (logic.mouseScrolled(mouseX, mouseY, scrollY)) {
      return true;
    }
    return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
  }

  @Override
  public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
    if (logic.keyPressed(keyCode, scanCode, modifiers)) {
      return true;
    }
    return super.keyPressed(keyCode, scanCode, modifiers);
  }

  @Override
  public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
    if (logic.keyReleased(keyCode, scanCode, modifiers)) {
      return true;
    }
    return super.keyReleased(keyCode, scanCode, modifiers);
  }

  @Override
  public boolean charTyped(char chr, int modifiers) {
    if (logic.charTyped(chr, modifiers)) {
      return true;
    }
    return super.charTyped(chr, modifiers);
  }

  @Override
  protected void slotClicked(@NotNull Slot slot, int slotId, int mouseButton, @NotNull ClickType type) {
    super.slotClicked(slot, slotId, mouseButton, type);
    logic.slotClicked(slot, slotId, mouseButton, type);
  }

  @Override
  protected void containerTick() {
    super.containerTick();
    logic.tick();
  }

  @Override
  public void onClose() {
    logic.onClose();
    super.onClose();
  }
}
