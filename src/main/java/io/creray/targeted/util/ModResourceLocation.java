package io.creray.targeted.util;

import io.creray.targeted.Targeted;
import net.minecraft.resources.ResourceLocation;

public final class ModResourceLocation {

    public static ResourceLocation of(String path) {
        return ResourceLocation.fromNamespaceAndPath(Targeted.MOD_ID, path);
    }

    private ModResourceLocation() {}
}
