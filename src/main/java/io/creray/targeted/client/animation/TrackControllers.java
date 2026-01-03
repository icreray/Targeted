package io.creray.targeted.client.animation;

import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.client.target.TargetContext;
import io.creray.targeted.util.EntityUtils;
import io.creray.targeted.util.ModIdentifier;
import lombok.experimental.UtilityClass;
import net.minecraft.core.Registry;

@UtilityClass
public class TrackControllers {

    public final TrackController HEALTH_PERCENT = (track, context) ->
        track.runTowards(EntityUtils.healthPercent(context.livingEntity()));

    public final TrackController TRANSITION_PROGRESS = new TrackController() {
        @Override
        public void enable(Track track) { track.runForward(); }

        @Override
        public void updateWith(Track track, TargetContext context) {}

        @Override
        public void disable(Track track) { track.runBackward(); }
    };

    public void registerAll() {
        register("health_percent", HEALTH_PERCENT);
        register("transition_progress", TRANSITION_PROGRESS);
    }

    private void register(String path, TrackController controller) {
        Registry.register(
            ModRegistries.TRACK_CONTROLLER,
            ModIdentifier.of(path),
            controller
        );
    }
}
