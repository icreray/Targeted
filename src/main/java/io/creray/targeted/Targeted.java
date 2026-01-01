package io.creray.targeted;

import io.creray.targeted.client.crosshair.Crosshair;
import io.creray.targeted.client.crosshair.mode.ModeMap;
import io.creray.targeted.client.resources.ModesLoader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.server.packs.PackType;

public class Targeted implements ClientModInitializer {

    public static final String MOD_ID = "targeted";

    public static final Crosshair crosshair;
    public static final ModeMap modes;

    static {
        crosshair = new Crosshair();
        modes = new ModeMap();
    }

    @Override
    public void onInitializeClient() {
        registerResources();
    }

    private static void registerResources() {
        var resourceLoader = ResourceLoader.get(PackType.CLIENT_RESOURCES);
        resourceLoader.registerReloader(
            ModesLoader.IDENTIFIER, new ModesLoader(modes)
        );
    }
}
