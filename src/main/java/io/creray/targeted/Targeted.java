package io.creray.targeted;

import io.creray.targeted.client.Crosshair;
import net.minecraft.client.Minecraft;

public final class Targeted {

    public static final String MOD_ID = "targeted";

    public static final Crosshair crosshair;

    static {
        crosshair = new Crosshair(Minecraft.getInstance());
    }

    private Targeted() {}
}
