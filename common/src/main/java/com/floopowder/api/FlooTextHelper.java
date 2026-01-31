package com.floopowder.api;

import net.minecraft.network.chat.Component;

import java.lang.reflect.Method;

/**
 * Version-agnostic helper for creating text Components.
 * Uses Component.literal() on 1.19+ and TextComponent on 1.18.x.
 */
public class FlooTextHelper {

  private static Method literalMethod;
  private static Class<?> textComponentClass;
  private static boolean initialized = false;
  private static boolean useLiteralMethod = false;

  private static void init() {
    if (initialized) return;
    initialized = true;

    // Try Component.literal() first (1.19+)
    try {
      literalMethod = Component.class.getMethod("literal", String.class);
      useLiteralMethod = true;
      return;
    } catch (NoSuchMethodException e) {
      // Not available, try TextComponent
    }

    // Try TextComponent (1.18.x)
    try {
      textComponentClass = Class.forName("net.minecraft.network.chat.TextComponent");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Cannot find Component.literal or TextComponent", e);
    }
  }

  /**
   * Creates a literal text component from a string.
   * Works on both 1.18.x (TextComponent) and 1.19+ (Component.literal).
   */
  public static Component literal(String text) {
    init();
    try {
      if (useLiteralMethod) {
        return (Component) literalMethod.invoke(null, text);
      } else {
        return (Component) textComponentClass.getConstructor(String.class).newInstance(text);
      }
    } catch (Exception e) {
      throw new RuntimeException("Failed to create literal component", e);
    }
  }
}
