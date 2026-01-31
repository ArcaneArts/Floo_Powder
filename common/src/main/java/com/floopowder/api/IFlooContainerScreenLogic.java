package com.floopowder.api;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

/**
 * Interface for container screen logic.
 * Implement this in common code to write container screen logic once,
 * then use IFlooScreenFactory to register version-specific screens.
 *
 * @param <T> The menu type
 */
public interface IFlooContainerScreenLogic<T extends AbstractContainerMenu> {

  /**
   * Called when the screen is initialized.
   * Set up widgets and initial state here.
   * Use context.setImageWidth/setImageHeight to configure container size.
   *
   * @param context The container screen context
   */
  void init(IFlooContainerScreenContext<T> context);

  /**
   * Renders the container background.
   * This is called before slots and items are rendered.
   *
   * @param graphics    Version-agnostic graphics wrapper
   * @param partialTick Partial tick time
   * @param mouseX      Mouse X position
   * @param mouseY      Mouse Y position
   */
  void renderBg(IFlooGraphics graphics, float partialTick, int mouseX, int mouseY);

  /**
   * Renders labels (text) on the container.
   * Called after the background, before slots.
   *
   * @param graphics Version-agnostic graphics wrapper
   * @param mouseX   Mouse X position
   * @param mouseY   Mouse Y position
   */
  default void renderLabels(IFlooGraphics graphics, int mouseX, int mouseY) {
  }

  /**
   * Renders tooltips.
   * Called after everything else is rendered.
   *
   * @param graphics Version-agnostic graphics wrapper
   * @param mouseX   Mouse X position
   * @param mouseY   Mouse Y position
   */
  default void renderTooltip(IFlooGraphics graphics, int mouseX, int mouseY) {
  }

  /**
   * Called when mouse is clicked.
   *
   * @param mouseX Mouse X position
   * @param mouseY Mouse Y position
   * @param button Mouse button (0=left, 1=right, 2=middle)
   * @return true if the click was handled (prevents default slot handling)
   */
  default boolean mouseClicked(double mouseX, double mouseY, int button) {
    return false;
  }

  /**
   * Called when mouse button is released.
   *
   * @param mouseX Mouse X position
   * @param mouseY Mouse Y position
   * @param button Mouse button
   * @return true if handled
   */
  default boolean mouseReleased(double mouseX, double mouseY, int button) {
    return false;
  }

  /**
   * Called when mouse is dragged.
   *
   * @param mouseX Mouse X position
   * @param mouseY Mouse Y position
   * @param button Mouse button being held
   * @param dragX  Drag delta X
   * @param dragY  Drag delta Y
   * @return true if handled
   */
  default boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
    return false;
  }

  /**
   * Called when mouse wheel is scrolled.
   * The vertical delta is normalized across versions.
   *
   * @param mouseX        Mouse X position
   * @param mouseY        Mouse Y position
   * @param verticalDelta Scroll amount (positive = up/forward)
   * @return true if handled
   */
  default boolean mouseScrolled(double mouseX, double mouseY, double verticalDelta) {
    return false;
  }

  /**
   * Called when a key is pressed.
   *
   * @param keyCode   The key code (GLFW key constants)
   * @param scanCode  The scan code
   * @param modifiers Modifier keys (shift, ctrl, alt)
   * @return true if handled
   */
  default boolean keyPressed(int keyCode, int scanCode, int modifiers) {
    return false;
  }

  /**
   * Called when a key is released.
   *
   * @param keyCode   The key code
   * @param scanCode  The scan code
   * @param modifiers Modifier keys
   * @return true if handled
   */
  default boolean keyReleased(int keyCode, int scanCode, int modifiers) {
    return false;
  }

  /**
   * Called when a character is typed.
   *
   * @param chr       The character
   * @param modifiers Modifier keys
   * @return true if handled
   */
  default boolean charTyped(char chr, int modifiers) {
    return false;
  }

  /**
   * Called when a slot is clicked.
   * This is called after default slot handling unless mouseClicked returned true.
   *
   * @param slot        The slot that was clicked, or null if not a slot
   * @param slotId      The slot ID
   * @param mouseButton The mouse button
   * @param type        The click type (normal, shift, swap, etc.)
   */
  default void slotClicked(Slot slot, int slotId, int mouseButton, ClickType type) {
  }

  /**
   * Called every tick (20 times per second).
   */
  default void tick() {
  }

  /**
   * Called when the screen is closed.
   */
  default void onClose() {
  }
}
