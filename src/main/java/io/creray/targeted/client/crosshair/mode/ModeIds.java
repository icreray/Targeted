package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.util.ModIdentifier;
import lombok.experimental.UtilityClass;
import net.minecraft.resources.Identifier;

// FIXME: This will be kept until data-driven mode selector come up
@Deprecated
@UtilityClass
public class ModeIds {
    public final Identifier HEALTH_INDICATOR;
    public final Identifier CIRCLE;
    public final Identifier EXPANDED;

    static {
        HEALTH_INDICATOR = ModIdentifier.of("health_indicator");
        CIRCLE = ModIdentifier.of("circle");
        EXPANDED = ModIdentifier.of("expanded");
    }
}
