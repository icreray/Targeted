package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.animation.CrosshairSprite;
import io.creray.targeted.util.ModIdentifier;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Map;

@Unmodifiable
public final class ModeMap {

    public static final Mode DEFAULT_MODE;
    public static final ModeMap EMPTY;

    private final Map<Identifier, Mode> modesById;

    static {
        DEFAULT_MODE = Mode.builder()
            .addSprite(CrosshairSprite.DEFAULT)
            .build();
        EMPTY = new ModeMap(Map.of());
    }

    public ModeMap(Map<Identifier, Mode> modesById) {
        this.modesById = modesById;
    }

    public @Nullable Mode get(Identifier id) {
        return modesById.get(id);
    }

    public Mode getOrDefault(Identifier id) {
        return modesById.getOrDefault(id, DEFAULT_MODE);
    }
}
