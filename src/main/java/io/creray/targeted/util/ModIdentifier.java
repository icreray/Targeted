package io.creray.targeted.util;

import io.creray.targeted.Targeted;
import lombok.experimental.UtilityClass;
import net.minecraft.resources.Identifier;

@UtilityClass
public class ModIdentifier {

    public Identifier of(String path) {
        return Identifier.fromNamespaceAndPath(Targeted.MOD_ID, path);
    }
}
