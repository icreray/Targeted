package io.creray.targeted.util;

import io.creray.targeted.Targeted;
import net.minecraft.resources.Identifier;

public final class ModIdentifier {

    public static Identifier of(String path) {
        return Identifier.fromNamespaceAndPath(Targeted.MOD_ID, path);
    }

    private ModIdentifier() {}
}
