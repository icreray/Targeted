package io.creray.targeted;

import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.client.crosshair.Crosshair;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.server.packs.PackType;

public class Targeted implements ClientModInitializer {

    public static final String MOD_ID = "targeted";

    public static final Crosshair crosshair;

    static {
        crosshair = new Crosshair();
    }

    @Override
    public void onInitializeClient() {
        ModRegistries.registerAll();
        registerResourceReloaders();
    }

    private static void registerResourceReloaders() {
        var resourceLoader = ResourceLoader.get(PackType.CLIENT_RESOURCES);
        crosshair.modeSelector.registerResourceReloaders(resourceLoader);
    }
}
