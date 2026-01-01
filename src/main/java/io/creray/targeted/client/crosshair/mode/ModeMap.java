package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.animation.CrosshairSprite;
import io.creray.targeted.util.ModIdentifier;
import net.minecraft.resources.Identifier;

import java.util.HashMap;

public final class ModeMap {

    public static final Identifier DEFAULT;
    public static final Mode DEFAULT_MODE;

    private final HashMap<Identifier, Mode> modesById;

    static {
        DEFAULT = ModIdentifier.of("default");
        DEFAULT_MODE = Mode.builder()
            .addSprite(CrosshairSprite.DEFAULT)
            .build();
    }

    public ModeMap() {
        modesById = new HashMap<>();
        reset();
    }

    public void put(Identifier id, Mode mode) {
        modesById.put(id, mode);
    }

    public Mode get(Identifier id) {
        return modesById.getOrDefault(id, DEFAULT_MODE);
    }

    public void reset() {
        modesById.clear();
        modesById.put(DEFAULT, DEFAULT_MODE);
    }
}
