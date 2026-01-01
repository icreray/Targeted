package io.creray.targeted.client.resources;

import io.creray.targeted.client.animation.CrosshairSprite;
import io.creray.targeted.client.animation.Track;
import io.creray.targeted.client.animation.TrackControllers;
import io.creray.targeted.client.crosshair.mode.Mode;
import io.creray.targeted.client.crosshair.mode.ModeBuilder;
import lombok.experimental.UtilityClass;

import static io.creray.targeted.client.resources.ModeDefinition.AnimationDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class ModeCompiler {

    public Mode compile(ModeDefinition def) {
        Map<String, Track> tracks = new HashMap<>();
        ModeBuilder builder = Mode.builder();

        createTracks(tracks, builder, def.animations());
        createAnimations(tracks, builder, def.animations());

        return builder.build();
    }

    private void createTracks(
        Map<String, Track> tracks,
        ModeBuilder builder,
        List<AnimationDefinition> animations
    ) {
        for (var animationDef : animations) {
            var trackDef = animationDef.track();
            tracks.computeIfAbsent(trackDef.id(), id -> {
                var track = new Track(trackDef.duration());
                builder.addTrack(track, TrackControllers.get(trackDef.controller()));
                return track;
            });
        }
    }

    private void createAnimations(
        Map<String, Track> tracks,
        ModeBuilder builder,
        List<AnimationDefinition> animations
    ) {
        for (var animationDef : animations) {
            var trackDef = animationDef.track();
            var track = tracks.get(trackDef.id());

            Track.Driver trackDriver = trackDef.limitedBy()
                .map(id -> Track.Driver.limitedBy(track, tracks.get(id)))
                .orElse(track::get);

            // FIXME: replace crosshair sprite calls with static methods
            builder.addAnimation(
                trackDriver,
                animationDef.sprites()
                    .stream()
                    .map(CrosshairSprite::new)
                    .toArray(CrosshairSprite[]::new)
            );
        }
    }
}
