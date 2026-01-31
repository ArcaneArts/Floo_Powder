package com.floopowder.api;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

/**
 * Factory for creating and registering advancement triggers.
 * Abstracts version-specific registration differences.
 */
public interface IFlooAdvancementFactory {

  /**
   * Creates a simple trigger that fires when trigger() is called.
   * The trigger will be registered automatically.
   *
   * @param id Unique identifier for this trigger
   * @return A supplier that returns the trigger after registration
   */
  <T extends CriterionTrigger<?>> Supplier<T> createSimpleTrigger(ResourceLocation id, Supplier<T> triggerSupplier);

  /**
   * Registers a pre-created trigger.
   * Handles version-specific registration (1-param vs 2-param).
   *
   * @param id      Trigger ID
   * @param trigger The trigger to register
   */
  void registerTrigger(ResourceLocation id, CriterionTrigger<?> trigger);
}
