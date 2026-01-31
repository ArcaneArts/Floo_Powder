package com.floopowder.api;

import java.util.function.Supplier;

public class Floo {
    private static IFlooRegistry registry;
    private static IFlooVersionHelper versionHelper;
    private static IFlooScreenHelper screenHelper;
    private static IFlooNetwork network;
    private static IFlooScreenFactory screenFactory;

    public static void setRegistry(IFlooRegistry registry) {
        Floo.registry = registry;
    }

    public static void setVersionHelper(IFlooVersionHelper helper) {
        Floo.versionHelper = helper;
    }

    public static IFlooRegistry getRegistry() {
        if (registry == null) {
            throw new IllegalStateException("Floo Registry has not been initialized!");
        }
        return registry;
    }

    public static IFlooVersionHelper version() {
        if (versionHelper == null) {
            throw new IllegalStateException("Floo Version Helper has not been initialized!");
        }
        return versionHelper;
    }

    /**
     * Returns true if the version helper is initialized.
     */
    public static boolean hasVersionHelper() {
        return versionHelper != null;
    }

    public static void setScreenHelper(IFlooScreenHelper helper) {
        Floo.screenHelper = helper;
    }

    public static IFlooScreenHelper screen() {
        if (screenHelper == null) {
            throw new IllegalStateException("Floo Screen Helper has not been initialized!");
        }
        return screenHelper;
    }

    public static void setNetwork(IFlooNetwork network) {
        Floo.network = network;
    }

    public static IFlooNetwork network() {
        if (network == null) {
            throw new IllegalStateException("Floo Network has not been initialized!");
        }
        return network;
    }

    /**
     * Returns true if the network is initialized.
     */
    public static boolean hasNetwork() {
        return network != null;
    }

    public static void setScreenFactory(IFlooScreenFactory factory) {
        Floo.screenFactory = factory;
    }

    public static IFlooScreenFactory screenFactory() {
        if (screenFactory == null) {
            throw new IllegalStateException("Floo Screen Factory has not been initialized!");
        }
        return screenFactory;
    }

    /**
     * Returns true if the screen factory is initialized.
     */
    public static boolean hasScreenFactory() {
        return screenFactory != null;
    }

    public static <T> Supplier<T> registerItem(String id, Supplier<T> itemSupplier) {
        return getRegistry().registerItem(id, itemSupplier);
    }

    public static <T> Supplier<T> registerBlock(String id, Supplier<T> blockSupplier) {
        return getRegistry().registerBlock(id, blockSupplier);
    }

    public static <T> Supplier<T> registerBlockEntity(String id, Supplier<T> blockEntitySupplier) {
        return getRegistry().registerBlockEntity(id, blockEntitySupplier);
    }

    public static <T> Supplier<T> registerEntity(String id, Supplier<T> entitySupplier) {
        return getRegistry().registerEntity(id, entitySupplier);
    }

    public static <T> Supplier<T> registerFluid(String id, Supplier<T> fluidSupplier) {
        return getRegistry().registerFluid(id, fluidSupplier);
    }

    public static <T> Supplier<T> registerSoundEvent(String id, Supplier<T> soundEventSupplier) {
        return getRegistry().registerSoundEvent(id, soundEventSupplier);
    }

    public static <T> Supplier<T> registerCreativeTab(String id, Supplier<T> tabSupplier) {
        return getRegistry().registerCreativeTab(id, tabSupplier);
    }

    public static <T> Supplier<T> registerMenuType(String id, Supplier<T> menuSupplier) {
        return getRegistry().registerMenuType(id, menuSupplier);
    }
}
