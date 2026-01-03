package io.creray.targeted.client.crosshair.rule;

import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.util.ModIdentifier;
import lombok.experimental.UtilityClass;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;

// FIXME: Stronger id invariants
@UtilityClass
public class ModeTriggers {

    public final ModeTrigger TARGET_ENTITY;
    public final ModeTrigger TARGET_BLOCK;
    public final ModeTrigger TARGET_EMPTY;

    private int count = 0;

    public int count() { return count; }

    static {
        TARGET_ENTITY = register("target_entity");
        TARGET_BLOCK = register("target_block");
        TARGET_EMPTY = register("target_empty");
    }

    public void registerAll() {
        // Initialize static constructor
    }

    private ModeTrigger register(String path) {
        return register(ModIdentifier.of(path));
    }

    private ModeTrigger register(Identifier identifier) {
        return Registry.register(
            ModRegistries.MODE_TRIGGER,
            identifier,
            new ModeTrigger(count++)
        );
    }
}
