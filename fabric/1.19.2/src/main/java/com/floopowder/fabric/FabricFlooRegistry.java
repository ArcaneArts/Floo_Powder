package com.floopowder.fabric;

import com.floopowder.api.IFlooRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class FabricFlooRegistry implements IFlooRegistry {
    private final String modId;

    public FabricFlooRegistry(String modId) {
        this.modId = modId;
    }

    private <V, T extends V> Supplier<T> register(Registry<V> registry, String id, Supplier<T> supplier) {
        T object = supplier.get();
        Registry.register(registry, new ResourceLocation(modId, id), object);
        return () -> object;
    }

    @Override
    public <T> Supplier<T> registerItem(String id, Supplier<T> itemSupplier) {
        return register((Registry) Registry.ITEM, id, itemSupplier);
    }

    @Override
    public <T> Supplier<T> registerBlock(String id, Supplier<T> blockSupplier) {
        return register((Registry) Registry.BLOCK, id, blockSupplier);
    }

    @Override
    public <T> Supplier<T> registerBlockEntity(String id, Supplier<T> blockEntitySupplier) {
        return register((Registry) Registry.BLOCK_ENTITY_TYPE, id, blockEntitySupplier);
    }

    @Override
    public <T> Supplier<T> registerEntity(String id, Supplier<T> entitySupplier) {
        return register((Registry) Registry.ENTITY_TYPE, id, entitySupplier);
    }

    @Override
    public <T> Supplier<T> registerFluid(String id, Supplier<T> fluidSupplier) {
        return register((Registry) Registry.FLUID, id, fluidSupplier);
    }

    @Override
    public <T> Supplier<T> registerSoundEvent(String id, Supplier<T> soundEventSupplier) {
        return register((Registry) Registry.SOUND_EVENT, id, soundEventSupplier);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Supplier<T> registerCreativeTab(String id, Supplier<T> tabSupplier) {
        // In 1.19.2, CreativeTabs are not registered in a registry.
        T tab = tabSupplier.get();
        return () -> tab;
    }

    @Override
    public <T> Supplier<T> registerMenuType(String id, Supplier<T> menuSupplier) {
        return register((Registry) Registry.MENU, id, menuSupplier);
    }
}