package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.animation.Animation;
import io.creray.targeted.client.animation.Track;
import io.creray.targeted.client.animation.TrackController;
import io.creray.targeted.client.target.TargetContext;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;

public final class Mode {

    private final Track[] tracks;
    private final TrackController[] controllers;
    private final Animation[] animations;

    private TargetContext context;

    public static ModeBuilder builder() {
        return new ModeBuilder();
    }

    Mode(
        Track[] tracks,
        TrackController[] controllers,
        Animation[] animations
    ) {
        assert tracks.length == controllers.length;
        this.tracks = tracks;
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
        for (int i = 0; i < tracks.length; i++) {
            controllers[i].enable(tracks[i]);
        }
    }

    public void tick() {
        for (int i = 0; i < tracks.length; i++) {
            controllers[i].updateWith(tracks[i], context);
        }
    }

    public void disable() {
        for (int i = 0; i < tracks.length; i++) {
            controllers[i].disable(tracks[i]);
        }
    }

    public boolean canBeSwitched() {
        return tracks.length == 0 || tracks[0].isZero();
    }

    private void updateSliders(float delta) {
        for (var slider : tracks) {
            slider.update(delta);
        }
    }
}