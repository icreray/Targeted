package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.animation.*;

import java.util.ArrayList;

public final class ModeBuilder {

    private final ArrayList<Track> tracks;
    private final ArrayList<TrackController> controllers;
    private final ArrayList<Animation> animations;

    ModeBuilder() {
        tracks = new ArrayList<>();
        controllers = new ArrayList<>();
        animations = new ArrayList<>();
    }

    public ModeBuilder addSlider(Track track, TrackController controller) {
        tracks.add(track);
        controllers.add(controller);
        return this;
    }

    public ModeBuilder addAnimation(Track.Driver trackDriver, CrosshairSprite[] frames) {
        animations.add(new Animation(trackDriver, frames));
        return this;
    }

    public ModeBuilder addSprite(CrosshairSprite sprite) {
        animations.add(new Animation(sprite));
        return this;
    }

    public Mode build() {
        return new Mode(
            tracks.toArray(Track[]::new),
            controllers.toArray(TrackController[]::new),
            animations.toArray(Animation[]::new)
        );
    }
}
