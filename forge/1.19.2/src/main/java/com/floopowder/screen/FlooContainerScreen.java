package com.floopowder.screen;

import com.floopowder.api.IFlooGraphics;
import com.floopowder.graphics.FlooGraphics;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;

/**
 * Forge 1.19.2 base container screen.
 * Handles version-specific method signatures (uses PoseStack, not GuiGraphics).
 */
public abstract class FlooContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

  public FlooContainerScreen(T menu, Inventory playerInventory, Component title) {
    super(menu, playerInventory, title);
  }

  @Override
  public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
    this.flooRenderBackground(poseStack, mouseX, mouseY, partialTick);
    super.render(poseStack, mouseX, mouseY, partialTick);
    this.renderTooltip(poseStack, mouseX, mouseY);
  }

  /**
   * Renders the background using the 1.19.2 API.
   */
  protected void flooRenderBackground(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
    this.renderBackground(poseStack);
  }

  @Override
  public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
    if (flooMouseScrolled(mouseX, mouseY, delta)) {
      return true;
    }
    return super.mouseScrolled(mouseX, mouseY, delta);
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
  protected IFlooGraphics createFlooGraphics(PoseStack poseStack) {
    return new FlooGraphics(poseStack);
  }
}
