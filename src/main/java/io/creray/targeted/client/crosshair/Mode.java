package io.creray.targeted.client.crosshair;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;

public interface Mode {

    default void tick() {}

    void render(GuiGraphics guiRenderer, DeltaTracker deltaTracker);

    default void enable() {}
    default void disable() {}

    default boolean canBeSwitched() {
        return true;
    }
}
