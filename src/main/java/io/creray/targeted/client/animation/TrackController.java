package io.creray.targeted.client.animation;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.client.target.TargetContext;

@FunctionalInterface
public interface TrackController {

    Codec<TrackController> TYPED_CODEC = ModRegistries.TRACK_CONTROLLER_TYPE
        .byNameCodec()
        .dispatch("type", TrackController::type, TrackController.Type::codec);
    Codec<TrackController> SIMPLE_CODEC = ModRegistries.SIMPLE_TRACK_CONTROLLER.byNameCodec();
    Codec<TrackController> CODEC = Codec.withAlternative(SIMPLE_CODEC, TYPED_CODEC);

    default void enable(Track track) {}

    void updateWith(Track track, TargetContext context);

    default void disable(Track track) {}

    default TrackController.Type type() { return TrackControllerTypes.SIMPLE; }

    record Type(MapCodec<? extends TrackController> codec) {}
}
