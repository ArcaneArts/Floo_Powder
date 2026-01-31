package com.floopowder.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Platform-agnostic loot modifier abstraction.
 * Handles differences between Forge GlobalLootModifier and Fabric loot table injection.
 */
public interface IFlooLootModifier {

  /**
   * Registers a loot modifier that adds items to loot tables.
   *
   * @param id           Unique identifier for this modifier
   * @param tableMatcher Predicate to match loot table IDs
   * @param modifier     Function that receives existing loot and context, returns modified loot
   */
  void registerModifier(
      ResourceLocation id,
      Predicate<ResourceLocation> tableMatcher,
      BiFunction<List<ItemStack>, LootContext, List<ItemStack>> modifier
  );

  /**
   * Registers a loot modifier for specific loot tables.
   *
   * @param id       Unique identifier for this modifier
   * @param tableIds List of loot table IDs to modify
   * @param modifier Function that receives existing loot and context, returns modified loot
   */
  default void registerModifier(
      ResourceLocation id,
      List<ResourceLocation> tableIds,
      BiFunction<List<ItemStack>, LootContext, List<ItemStack>> modifier
  ) {
    registerModifier(id, tableIds::contains, modifier);
  }

  /**
   * Registers a simple loot adder that adds items with a chance.
   *
   * @param id       Unique identifier
   * @param tableIds Tables to modify
   * @param chance   Chance (0.0 to 1.0) of adding items
   * @param items    Supplier for items to add
   */
  void registerSimpleAdder(
      ResourceLocation id,
      List<ResourceLocation> tableIds,
      float chance,
      java.util.function.Supplier<List<ItemStack>> items
  );
}
