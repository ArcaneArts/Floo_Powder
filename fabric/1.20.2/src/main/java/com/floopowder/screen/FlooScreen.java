package com.floopowder.screen;

import com.floopowder.api.IFlooGraphics;
import com.floopowder.graphics.FlooGraphics;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Fabric 1.20.2 base screen for non-container screens.
 * Handles version-specific method signatures.
 */
public abstract class FlooScreen extends Screen {

  protected FlooScreen(Component title) {
    super(title);
  }

  @Override
  public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
    this.flooRenderBackground(guiGraphics, mouseX, mouseY, partialTick);
    super.render(guiGraphics, mouseX, mouseY, partialTick);
  }

  /**
   * Renders the background using the 1.20.2 API.
   */
  protected void flooRenderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
    this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
  }

  @Override
  public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
    if (flooMouseScrolled(mouseX, mouseY, scrollY)) {
      return true;
    }
    return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
  }

  /**
   * Handle mouse scroll with normalized vertical delta.
   */
  protected boolean flooMouseScrolled(double mouseX, double mouseY, double verticalDelta) {
    return false;
  }

  /**
   * Creates a version-agnostic graphics wrapper.
   */
  protected IFlooGraphics createFlooGraphics(GuiGraphics guiGraphics) {
    return new FlooGraphics(guiGraphics);
  }
}
