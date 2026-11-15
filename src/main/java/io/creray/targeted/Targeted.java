package io.creray.targeted;

import com.mojang.logging.LogUtils;
import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.client.crosshair.Crosshair;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.server.packs.PackType;
import org.slf4j.Logger;

public class Targeted implements ClientModInitializer {

    public static final String MOD_ID = "targeted";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final Crosshair crosshair;

    static {
        crosshair = new Crosshair();
    }

    @Override
    public void onInitializeClient() {
        ModRegistries.registerAll();
        registerResourceReloaders();
        ClientTickEvents.END_CLIENT_TICK.register(_ -> crosshair.tick());
    }

    private static void registerResourceReloaders() {
        var resourceLoader = ResourceLoader.get(PackType.CLIENT_RESOURCES);
        crosshair.modeSelector.registerResourceReloaders(resourceLoader);
    }
}
