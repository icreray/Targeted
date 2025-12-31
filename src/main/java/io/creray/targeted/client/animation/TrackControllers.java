package io.creray.targeted.client.animation;

import io.creray.targeted.client.target.TargetContext;
import io.creray.targeted.util.EntityUtils;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class TrackControllers {

    public final TrackController HEALTH_PERCENT = (track, context) -> {
        track.runTowards(EntityUtils.healthPercent(context.livingEntity()));
    };

    public final TrackController TRANSITION_PROGRESS = new TrackController() {
        @Override
        public void enable(Track track) { track.runForward(); }

        @Override
        public void updateWith(Track track, TargetContext context) {}

        @Override
        public void disable(Track track) { track.runBackward(); }
    };

    private final Map<String, TrackController> BY_KEY = Map.of(
        "health_percent", HEALTH_PERCENT,
        "transition_progress", TRANSITION_PROGRESS
    );

    public TrackController get(String key) {
        return BY_KEY.get(key);
    }
}
