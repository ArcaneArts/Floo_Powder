package com.floopowder.screen;

import com.floopowder.api.IFlooGraphics;
import com.floopowder.api.IFlooScreenContext;
import com.floopowder.api.IFlooScreenLogic;
import com.floopowder.graphics.FlooGraphics;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Forge 1.20.2 screen that delegates to IFlooScreenLogic.
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
  public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
    IFlooGraphics graphics = new FlooGraphics(guiGraphics);

    // Render background
    this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
    logic.renderBackground(graphics, mouseX, mouseY, partialTick);

    // Render widgets
    super.render(guiGraphics, mouseX, mouseY, partialTick);

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
  public <T extends GuiEventListener & Renderable & NarratableEntry> T addRenderableWidgetPublic(T widget) {
    return super.addRenderableWidget(widget);
  }

  public <T extends Renderable> T addRenderableOnlyPublic(T widget) {
    return super.addRenderableOnly(widget);
  }

  public void removeWidgetPublic(GuiEventListener listener) {
    super.removeWidget(listener);
  }

  public void clearWidgetsPublic() {
    super.clearWidgets();
  }
}
