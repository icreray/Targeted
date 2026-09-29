package io.creray.targeted.client.resources;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.creray.targeted.client.animation.TrackController;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.List;
import java.util.Optional;

public record ModeDefinition(
    List<TrackDefinition> tracks,
    List<AnimationDefinition> animations
) {
    public static final Codec<ModeDefinition> CODEC = RecordCodecBuilder.<ModeDefinition>create(
        instance -> instance.group(
            TrackDefinition.CODEC.listOf().fieldOf("tracks").forGetter(ModeDefinition::tracks),
            AnimationDefinition.CODEC.listOf().fieldOf("animations").forGetter(ModeDefinition::animations)
        ).apply(instance, ModeDefinition::new)
    ).validate(ModeDefinition::validate);

    @VisibleForTesting
    public DataResult<ModeDefinition> validate() {
        if (animations.isEmpty())
            return DataResult.error(() -> "Animations cannot be empty");
        return DataResult.success(this);
    }

    public record AnimationDefinition(
        String trackId,
        Optional<String> limitedBy,
        List<Identifier> sprites
    ) {
        public static final Codec<AnimationDefinition> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                Codec.STRING.fieldOf("track_id").forGetter(AnimationDefinition::trackId),
                Codec.STRING.optionalFieldOf("limited_by").forGetter(AnimationDefinition::limitedBy),
                Identifier.CODEC.listOf().fieldOf("sprites").forGetter(AnimationDefinition::sprites)
            ).apply(instance, AnimationDefinition::new)
        );
    }

    public record TrackDefinition(
        String id,
        float duration,
        TrackController controller
    ) {
        public static final Codec<TrackDefinition> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                Codec.STRING.fieldOf("id").forGetter(TrackDefinition::id),
                Codec.floatRange(0, 100).fieldOf("duration").forGetter(TrackDefinition::duration),
                TrackController.CODEC.fieldOf("controller").forGetter(TrackDefinition::controller)
            ).apply(instance, TrackDefinition::new)
        );
    }
}
