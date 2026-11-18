package io.creray.targeted;

import com.mojang.logging.LogUtils;
import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.client.crosshair.Crosshair;
import io.creray.targeted.util.ModIdentifier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
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
        ClientTickEvents.END_CLIENT_TICK.register(_ -> crosshair.tick());
        registerDefaultPack();
        registerResourceReloaders();
    }

    private static void registerDefaultPack() {
        var modContainer = FabricLoader.getInstance()
            .getModContainer(MOD_ID)
            .orElseThrow(() -> new IllegalStateException("Could not find ModContainer for mod ID: " + MOD_ID));
        ResourceLoader.registerBuiltinPack(
            ModIdentifier.of("targeted_default"),
            modContainer,
            Component.translatable("pack.targeted_default.name"),
            PackActivationType.DEFAULT_ENABLED
        );
    }

    private static void registerResourceReloaders() {
        var resourceLoader = ResourceLoader.get(PackType.CLIENT_RESOURCES);
        crosshair.modeSelector.registerResourceReloaders(resourceLoader);
    }
}
