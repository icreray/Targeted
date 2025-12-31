package io.creray.targeted.client.animation;

import io.creray.targeted.client.target.TargetContext;

@FunctionalInterface
public interface TrackController {

    default void enable(Track track) {}

    void updateWith(Track track, TargetContext context);

    default void disable(Track track) {}
}
