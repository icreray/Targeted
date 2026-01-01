package io.creray.targeted.client.crosshair;

import io.creray.targeted.client.crosshair.mode.Mode;
import io.creray.targeted.client.crosshair.mode.ModeMap;
import io.creray.targeted.client.target.Target;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

public final class Crosshair {

    private Target target;
    private Mode currentMode;
    @Nullable
    private Mode pendingMode;

    {
        target = Target.empty();
        currentMode = ModeMap.DEFAULT_MODE;
    }

    public void updateTarget(Target newTarget) {
        if (newTarget.equals(target)) return;
        target = newTarget;
        selectNextMode();
    }

    public void tick() {
        currentMode.tick();
        if (pendingMode != null && currentMode.canBeSwitched()) {
            switchToNextMode();
        }
    }

    public void render(GuiGraphics guiRenderer, DeltaTracker deltaTracker) {
        currentMode.render(guiRenderer, deltaTracker);
    }

    private void selectNextMode() {
        var selectedMode = ModeSelector.selectFor(target);
        if (selectedMode == currentMode) {
            pendingMode = null;
            currentMode.enable();
        }
        else {
            pendingMode = selectedMode;
            currentMode.disable();
        }
    }

    private void switchToNextMode() {
        assert pendingMode != null;
        currentMode = pendingMode;
        currentMode.enable();
        pendingMode = null;
    }
}
