package com.floopowder.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Predicate;

/**
 * Platform-agnostic advancement trigger abstraction.
 * Handles API differences between Minecraft versions:
 * - Pre-1.20.2: EntityPredicate.Composite, getId() required, 1-param registration
 * - Post-1.20.2: Optional<ContextAwarePredicate>, no getId(), 2-param registration
 *
 * Implementations should extend SimpleCriterionTrigger in platform modules.
 *
 * @param <T> The trigger instance type (usually an inner TriggerInstance class)
 */
public interface IFlooAdvancementTrigger<T> {

  /**
   * Gets the trigger ID.
   */
  ResourceLocation getId();

  /**
   * Triggers this advancement for a player.
   * The predicate allows filtering which trigger instances should fire.
   *
   * @param player    The player to trigger for
   * @param predicate Filter for trigger instances (usually returns true)
   */
  void trigger(ServerPlayer player, Predicate<T> predicate);

  /**
   * Triggers this advancement for a player with no filtering.
   */
  default void trigger(ServerPlayer player) {
    trigger(player, instance -> true);
  }
}
