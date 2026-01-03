package io.creray.targeted.client.resources;

import io.creray.targeted.client.animation.CrosshairSprite;
import io.creray.targeted.client.animation.Track;
import io.creray.targeted.client.crosshair.mode.Mode;
import io.creray.targeted.client.crosshair.mode.ModeBuilder;
import lombok.experimental.UtilityClass;

import static io.creray.targeted.client.resources.ModeDefinition.AnimationDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class ModeCompiler {
    public Mode compile(ModeDefinition def) throws IllegalStateException {
        Map<String, Track> definedTracks = new HashMap<>();
        ModeBuilder builder = Mode.builder();

        createTracks(definedTracks, builder, def.animations());
        createAnimations(definedTracks, builder, def.animations());

        return builder.build();
    }

    private void createTracks(
        Map<String, Track> definedTracks,
        ModeBuilder builder,
        List<AnimationDefinition> animations
    ) {
        for (var animationDef : animations) {
            var trackDef = animationDef.track();
            definedTracks.computeIfAbsent(trackDef.id(), id -> {
                var track = new Track(trackDef.duration());
                builder.addTrack(track, trackDef.controller());
                return track;
            });
        }
    }

    private void createAnimations(
        Map<String, Track> definedTracks,
        ModeBuilder builder,
        List<AnimationDefinition> animations
    ) throws IllegalStateException {
        for (var animationDef : animations) {
            var trackDef = animationDef.track();
            var track = definedTracks.get(trackDef.id());

            Track.Driver trackDriver = trackDef.limitedBy()
                .map(id -> createLimitedByDriver(track, definedTracks, id))
                .orElse(track::get);

            builder.addAnimation(
                trackDriver,
                animationDef.sprites()
                    .stream()
                    .map(CrosshairSprite::new)
                    .toArray(CrosshairSprite[]::new)
            );
        }
    }

    private Track.Driver createLimitedByDriver(
        Track track,
        Map<String, Track> definedTracks,
        String id
    ) throws IllegalStateException {
        var limiter = definedTracks.get(id);
        if (limiter == null)
            throw new IllegalStateException("Invalid limited_by argument '" + id + "'. It must reference a defined track.");
        return Track.Driver.limitedBy(track, limiter);
    }
}
