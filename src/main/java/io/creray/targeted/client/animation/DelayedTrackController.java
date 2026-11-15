package io.creray.targeted.client.animation;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.creray.targeted.client.target.TargetContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;


@Accessors(fluent = true)
@RequiredArgsConstructor
public final class DelayedTrackController implements TrackController {

    public static final MapCodec<DelayedTrackController> CODEC;

    @Getter
    private final int enableDelay;
    @Getter
    private final int disableDelay;
    @Getter
    private final TrackController inner;

    private State pendingState = State.DISABLED;
    private int ticks = 0;

    @Override
    public void enable(Track track) {
        if (enableDelay == 0) {
            cancelPending();
            inner.enable(track);
        }
        else
            schedule(State.ENABLED, enableDelay);
    }

    @Override
    public void updateWith(Track track, TargetContext context) {
        inner.updateWith(track, context);
        if (ticks > 0 && --ticks == 0) {
            switch (pendingState) {
                case ENABLED -> inner.enable(track);
                case DISABLED -> inner.disable(track);
            }
        }
    }

    @Override
    public void disable(Track track) {
        if (disableDelay == 0) {
            cancelPending();
            inner.disable(track);
        }
        else
            schedule(State.DISABLED, disableDelay);
    }

    private void cancelPending() {
        ticks = 0;
    }

    private void schedule(State state, int delay) {
        pendingState = state;
        ticks = delay;
    }

    @Override
    public TrackController.Type type() {
        return TrackControllerTypes.DELAYED;
    }

    static {
        CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                Codec.intRange(0, 1000).optionalFieldOf("enable", 0).forGetter(DelayedTrackController::enableDelay),
                Codec.intRange(0, 1000).optionalFieldOf("disable", 0).forGetter(DelayedTrackController::disableDelay),
                TrackController.CODEC.fieldOf("controller").forGetter(DelayedTrackController::inner)
            ).apply(instance, DelayedTrackController::new)
        );
    }

    private enum State { ENABLED, DISABLED }
}
