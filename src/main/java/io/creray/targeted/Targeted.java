package io.creray.targeted;

import io.creray.targeted.client.crosshair.Crosshair;

public final class Targeted {

    public static final String MOD_ID = "targeted";

    public static final Crosshair crosshair;

    static {
        crosshair = new Crosshair();
    }

    private Targeted() {}
}
