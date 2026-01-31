package com.floopowder.forge;

import com.floopowder.api.IFlooRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ForgeFlooRegistry implements IFlooRegistry {
    private final DeferredRegister<Item> items;
    private final DeferredRegister<Block> blocks;
    private final DeferredRegister<BlockEntityType<?>> blockEntities;
    private final DeferredRegister<EntityType<?>> entities;
    private final DeferredRegister<Fluid> fluids;
    private final DeferredRegister<SoundEvent> soundEvents;
    private final DeferredRegister<CreativeModeTab> creativeTabs;
    private final DeferredRegister<MenuType<?>> menuTypes;

    public ForgeFlooRegistry(String modId, IEventBus eventBus) {
        this.items = DeferredRegister.create(ForgeRegistries.ITEMS, modId);
        this.blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, modId);
        this.blockEntities = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, modId);
        this.entities = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, modId);
        this.fluids = DeferredRegister.create(ForgeRegistries.FLUIDS, modId);
        this.soundEvents = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, modId);
        this.creativeTabs = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, modId);
        this.menuTypes = DeferredRegister.create(ForgeRegistries.MENU_TYPES, modId);

        this.items.register(eventBus);
        this.blocks.register(eventBus);
        this.blockEntities.register(eventBus);
        this.entities.register(eventBus);
        this.fluids.register(eventBus);
        this.soundEvents.register(eventBus);
        this.creativeTabs.register(eventBus);
        this.menuTypes.register(eventBus);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Supplier<T> registerItem(String id, Supplier<T> itemSupplier) {
        return (Supplier<T>) items.register(id, (Supplier<Item>) itemSupplier);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Supplier<T> registerBlock(String id, Supplier<T> blockSupplier) {
        return (Supplier<T>) blocks.register(id, (Supplier<Block>) blockSupplier);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Supplier<T> registerBlockEntity(String id, Supplier<T> blockEntitySupplier) {
        return (Supplier<T>) blockEntities.register(id, (Supplier<BlockEntityType<?>>) blockEntitySupplier);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Supplier<T> registerEntity(String id, Supplier<T> entitySupplier) {
        return (Supplier<T>) entities.register(id, (Supplier<EntityType<?>>) entitySupplier);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Supplier<T> registerFluid(String id, Supplier<T> fluidSupplier) {
        return (Supplier<T>) fluids.register(id, (Supplier<Fluid>) fluidSupplier);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Supplier<T> registerSoundEvent(String id, Supplier<T> soundEventSupplier) {
        return (Supplier<T>) soundEvents.register(id, (Supplier<SoundEvent>) soundEventSupplier);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Supplier<T> registerCreativeTab(String id, Supplier<T> tabSupplier) {
        return (Supplier<T>) creativeTabs.register(id, (Supplier<CreativeModeTab>) tabSupplier);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Supplier<T> registerMenuType(String id, Supplier<T> menuSupplier) {
        return (Supplier<T>) menuTypes.register(id, (Supplier<MenuType<?>>) menuSupplier);
    }
}