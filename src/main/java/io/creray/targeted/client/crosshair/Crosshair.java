package io.creray.targeted.client.crosshair;

import io.creray.targeted.client.target.Target;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

public final class Crosshair {

    private Target target;
    private Mode currentMode;
    @Nullable
    private Mode nextMode;

    {
        target = Target.empty();
        currentMode = Modes.DEFAULT;
    }

    public void updateTarget(Target newTarget) {
        if (newTarget.equals(target)) return;
        target = newTarget;
        selectNextMode();
    }

    public void tick() {
        currentMode.tick();
        if (nextMode != null && currentMode.canBeSwitched()) {
            switchToNextMode();
        }
    }

    public void render(GuiGraphics guiRenderer, DeltaTracker deltaTracker) {
        currentMode.render(guiRenderer, deltaTracker);
    }

    private void selectNextMode() {
        var selectedMode = ModeSelector.selectFor(target);
        if (selectedMode == currentMode) {
            nextMode = null;
            currentMode.enable();
        }
        else {
            nextMode = selectedMode;
            currentMode.disable();
        }
    }

    private void switchToNextMode() {
        assert nextMode != null;
        currentMode = nextMode;
        currentMode.enable();
        nextMode = null;
    }
}
