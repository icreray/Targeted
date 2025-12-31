package io.creray.targeted.client.animation;

import io.creray.targeted.client.target.TargetContext;
import io.creray.targeted.util.EntityUtils;
import io.creray.targeted.util.ModIdentifier;
import lombok.experimental.UtilityClass;
import net.minecraft.resources.Identifier;

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

    private final Map<Identifier, TrackController> REGISTRY = Map.of(
        ModIdentifier.of("health_percent"), HEALTH_PERCENT,
        ModIdentifier.of("transition_progress"), TRANSITION_PROGRESS
    );

    public TrackController get(Identifier id) {
        return REGISTRY.get(id);
    }
}
