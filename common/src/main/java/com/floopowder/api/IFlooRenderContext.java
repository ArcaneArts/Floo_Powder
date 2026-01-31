package com.floopowder.api;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;

/**
 * Platform-agnostic render context for entity and block entity rendering.
 * Provides access to rendering primitives across Minecraft versions.
 */
public interface IFlooRenderContext {

  /**
   * Gets the pose stack for matrix transformations.
   */
  PoseStack getPoseStack();

  /**
   * Gets the buffer source for rendering.
   */
  MultiBufferSource getBufferSource();

  /**
   * Gets the packed light value.
   */
  int getPackedLight();

  /**
   * Gets the packed overlay value.
   */
  int getPackedOverlay();

  /**
   * Gets the partial tick for interpolation.
   */
  float getPartialTick();

  /**
   * Push a pose onto the stack.
   */
  default void pushPose() {
    getPoseStack().pushPose();
  }

  /**
   * Pop a pose from the stack.
   */
  default void popPose() {
    getPoseStack().popPose();
  }

  /**
   * Translate the current pose.
   */
  default void translate(double x, double y, double z) {
    getPoseStack().translate(x, y, z);
  }

  /**
   * Scale the current pose.
   */
  default void scale(float x, float y, float z) {
    getPoseStack().scale(x, y, z);
  }
}
