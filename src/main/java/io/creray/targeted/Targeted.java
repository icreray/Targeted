package io.creray.targeted;

import io.creray.targeted.client.crosshair.Crosshair;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Targeted {

    public final String MOD_ID = "targeted";

    public final Crosshair crosshair;

    static {
        crosshair = new Crosshair();
    }
}
