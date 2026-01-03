package io.creray.targeted.client;

import io.creray.targeted.client.animation.TrackController;
import io.creray.targeted.client.animation.TrackControllers;
import lombok.experimental.UtilityClass;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.core.Registry;

@UtilityClass
public class ModRegistries {

    public final Registry<TrackController> TRACK_CONTROLLER;

    static {
        TRACK_CONTROLLER = FabricRegistryBuilder
            .createSimple(ModRegistryKeys.TRACK_CONTROLLER)
            .buildAndRegister();
    }

    public void registerAll() {
        TrackControllers.registerAll();
    }
}
