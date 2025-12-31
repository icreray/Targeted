package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.animation.*;
import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public final class Modes {

    public final Mode DEFAULT = sprite(CrosshairSprite.DEFAULT);
    public final Mode HEALTH_INDICATOR = createHealthIndicator();
    public final Mode CIRCLE = transition(3, CrosshairSprite.SHRINK);
    public final Mode EXPANDED = transition(3, Arrays.copyOf(CrosshairSprite.SHRINK, 2));

    private Mode sprite(CrosshairSprite sprite) {
        return Mode.builder()
            .addSprite(sprite)
            .build();
    }

    private Mode transition(float duration, CrosshairSprite[] animation) {
        var track = new Track(duration);
        return Mode.builder()
            .addTrack(track, TrackControllers.TRANSITION_PROGRESS)
            .addAnimation(track::get, animation)
            .build();
    }

    private Mode createHealthIndicator() {
        var transition = new Track(8);
        var indicator = new Track(6);

        return Mode.builder()
            .addTrack(transition, TrackControllers.TRANSITION_PROGRESS)
            .addTrack(indicator, TrackControllers.HEALTH_PERCENT)
            .addAnimation(transition::get, CrosshairSprite.EXPAND)
            .addAnimation(Track.Driver.limitedBy(indicator, transition), CrosshairSprite.HEALTH_INDICATOR)
            .build();
    }
}
