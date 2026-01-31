package com.floopowder.screen;

import com.floopowder.api.IFlooGraphics;
import com.floopowder.graphics.FlooGraphics;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;

/**
 * NeoForge 1.20.2 base container screen.
 * Handles version-specific method signatures.
 */
public abstract class FlooContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

  public FlooContainerScreen(T menu, Inventory playerInventory, Component title) {
    super(menu, playerInventory, title);
  }

  @Override
  public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
    this.flooRenderBackground(guiGraphics, mouseX, mouseY, partialTick);
    super.render(guiGraphics, mouseX, mouseY, partialTick);
    this.renderTooltip(guiGraphics, mouseX, mouseY);
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
   * Override this instead of mouseScrolled for version-independent scroll handling.
   *
   * @param mouseX Mouse X position
   * @param mouseY Mouse Y position
   * @param verticalDelta The vertical scroll amount (positive = up)
   * @return true if handled
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
