package com.floopowder.api;

import java.util.function.Supplier;

public interface IFlooRegistry {
    <T> Supplier<T> registerItem(String id, Supplier<T> itemSupplier);
    <T> Supplier<T> registerBlock(String id, Supplier<T> blockSupplier);
    <T> Supplier<T> registerBlockEntity(String id, Supplier<T> blockEntitySupplier);
    <T> Supplier<T> registerEntity(String id, Supplier<T> entitySupplier);
    <T> Supplier<T> registerFluid(String id, Supplier<T> fluidSupplier);
    <T> Supplier<T> registerSoundEvent(String id, Supplier<T> soundEventSupplier);
    <T> Supplier<T> registerCreativeTab(String id, Supplier<T> tabSupplier);
    <T> Supplier<T> registerMenuType(String id, Supplier<T> menuSupplier);
}
