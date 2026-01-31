package com.floopowder.screen;

import com.floopowder.api.IFlooGraphics;
import com.floopowder.graphics.FlooGraphics;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Forge 1.18.2 base screen for non-container screens.
 * Handles version-specific method signatures (uses PoseStack).
 */
public abstract class FlooScreen extends Screen {

  protected FlooScreen(Component title) {
    super(title);
  }

  @Override
  public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
    this.flooRenderBackground(poseStack, mouseX, mouseY, partialTick);
    super.render(poseStack, mouseX, mouseY, partialTick);
  }

  /**
   * Renders the background using the 1.18.2 API.
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
