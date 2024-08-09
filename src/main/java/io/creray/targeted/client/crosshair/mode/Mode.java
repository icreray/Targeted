package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.animation.Animation;
import io.creray.targeted.client.animation.Slider;
import io.creray.targeted.client.animation.SliderController;
import io.creray.targeted.client.target.TargetContext;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;

public final class Mode {

    private final Slider[] sliders;
    private final SliderController[] controllers;
    private final Animation[] animations;

    private TargetContext context;

    public Mode(
            Slider[] sliders,
            SliderController[] controllers,
            Animation[] animations
    ) {
        assert sliders.length == controllers.length;
        this.sliders = sliders;
        this.controllers = controllers;
        this.animations = animations;
    }

    public Mode with(TargetContext context) {
        this.context = context;
        return this;
    }

    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        updateSliders(deltaTracker.getGameTimeDeltaTicks());
        for (var animation : animations) {
            animation.renderFrame(guiGraphics);
        }
    }

    public void enable() {
        for (int i = 0; i < sliders.length; i++) {
            controllers[i].enable(sliders[i]);
        }
    }

    public void tick() {
        for (int i = 0; i < sliders.length; i++) {
            controllers[i].updateWith(sliders[i], context);
        }
    }

    public void disable() {
        for (int i = 0; i < sliders.length; i++) {
            controllers[i].disable(sliders[i]);
        }
    }

    public boolean canBeSwitched() {
        return sliders.length == 0 || sliders[0].isZero();
    }

    private void updateSliders(float delta) {
        for (var slider : sliders) {
            slider.update(delta);
        }
    }
}
