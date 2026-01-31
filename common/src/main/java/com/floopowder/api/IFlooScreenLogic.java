package com.floopowder.api;

/**
 * Interface for non-container screen logic.
 * Implement this in common code to write screen logic once,
 * then use IFlooScreenFactory to create version-specific screens.
 */
public interface IFlooScreenLogic {

  /**
   * Called when the screen is initialized.
   * Set up widgets and initial state here.
   *
   * @param context The screen context providing access to screen utilities
   */
  void init(IFlooScreenContext context);

  /**
   * Renders the screen content.
   * Called every frame after the background is rendered.
   *
   * @param graphics    Version-agnostic graphics wrapper
   * @param mouseX      Mouse X position
   * @param mouseY      Mouse Y position
   * @param partialTick Partial tick time for smooth animation
   */
  void render(IFlooGraphics graphics, int mouseX, int mouseY, float partialTick);

  /**
   * Renders the screen background.
   * Override to customize background rendering.
   *
   * @param graphics    Version-agnostic graphics wrapper
   * @param mouseX      Mouse X position
   * @param mouseY      Mouse Y position
   * @param partialTick Partial tick time
   */
  default void renderBackground(IFlooGraphics graphics, int mouseX, int mouseY, float partialTick) {
    // Default implementation does nothing - the adapter handles default background
  }

  /**
   * Called when mouse is clicked.
   *
   * @param mouseX Mouse X position
   * @param mouseY Mouse Y position
   * @param button Mouse button (0=left, 1=right, 2=middle)
   * @return true if the click was handled
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
   * Called every tick (20 times per second).
   */
  default void tick() {
  }

  /**
   * Called when the screen is closed.
   */
  default void onClose() {
  }

  /**
   * Whether this screen pauses the game in singleplayer.
   *
   * @return true to pause the game
   */
  default boolean isPauseScreen() {
    return true;
  }
}
