package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.animation.AnimationProgress;
import io.creray.targeted.client.animation.SpriteAnimation;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;

public class SimpleMode implements Mode {

    protected final AnimationProgress transition;
    private final SpriteAnimation transitionAnimation;

    public SimpleMode(float transitionDuration, SpriteAnimation transitionAnimation) {
        this.transition = new AnimationProgress(transitionDuration);
        this.transitionAnimation = transitionAnimation;
    }

    @Override
    public void render(GuiGraphics guiRenderer, DeltaTracker deltaTracker) {
        transition.update(deltaTracker.getGameTimeDeltaTicks());
        transitionAnimation.getFrame(transition).renderAtCenter(guiRenderer);
    }

    @Override
    public final void enable() {
        transition.runForward();
    }

    @Override
    public final void disable() {
        transition.runBackward();
    }

    @Override
    public final boolean canBeSwitched() {
        return transition.isZero();
    }
}
