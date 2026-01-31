package com.floopowder.screen;

import com.floopowder.api.IFlooGraphics;
import com.floopowder.api.IFlooScreenContext;
import com.floopowder.api.IFlooScreenLogic;
import com.floopowder.graphics.FlooGraphics;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Forge 1.18.2 screen that delegates to IFlooScreenLogic.
 */
public class FlooLogicScreen extends Screen {

  private final IFlooScreenLogic logic;
  private final IFlooScreenContext context;

  public FlooLogicScreen(Component title, IFlooScreenLogic logic) {
    super(title);
    this.logic = logic;
    this.context = new FlooScreenContext(this);
  }

  @Override
  protected void init() {
    super.init();
    logic.init(context);
  }

  @Override
  public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
    IFlooGraphics graphics = new FlooGraphics(poseStack);

    // Render background
    this.renderBackground(poseStack);
    logic.renderBackground(graphics, mouseX, mouseY, partialTick);

    // Render widgets
    super.render(poseStack, mouseX, mouseY, partialTick);

    // Render logic content
    logic.render(graphics, mouseX, mouseY, partialTick);
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
  public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
    // 1.18.2 uses 3-param mouseScrolled, delta is the vertical scroll
    if (logic.mouseScrolled(mouseX, mouseY, delta)) {
      return true;
    }
    return super.mouseScrolled(mouseX, mouseY, delta);
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
  public void tick() {
    super.tick();
    logic.tick();
  }

  @Override
  public void onClose() {
    logic.onClose();
    super.onClose();
  }

  @Override
  public boolean isPauseScreen() {
    return logic.isPauseScreen();
  }

  // Accessor methods for FlooScreenContext
  public <T extends GuiEventListener & Widget & NarratableEntry> T addRenderableWidgetPublic(T widget) {
    return super.addRenderableWidget(widget);
  }

  public <T extends Widget> T addRenderableOnlyPublic(T widget) {
    return super.addRenderableOnly(widget);
  }

  public void removeWidgetPublic(GuiEventListener listener) {
    super.removeWidget(listener);
  }

  public void clearWidgetsPublic() {
    super.clearWidgets();
  }
}
