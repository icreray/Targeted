package io.creray.targeted.client.resources;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;

import java.util.List;
import java.util.Optional;

public record ModeDefinition(
    List<AnimationDefinition> animations
) {
    public static final Codec<ModeDefinition> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            AnimationDefinition.CODEC.listOf().fieldOf("animations").forGetter(ModeDefinition::animations)
        ).apply(instance, ModeDefinition::new)
    );

    public record AnimationDefinition(
        TrackDefinition track,
        List<Identifier> sprites
    ) {
        public static final Codec<AnimationDefinition> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                TrackDefinition.CODEC.fieldOf("track").forGetter(AnimationDefinition::track),
                Identifier.CODEC.listOf().fieldOf("sprites").forGetter(AnimationDefinition::sprites)
            ).apply(instance, AnimationDefinition::new)
        );
    }

    public record TrackDefinition(
        String id,
        float duration,
        Identifier controller,
        Optional<String> limitedBy
    ) {
        public static final Codec<TrackDefinition> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                Codec.STRING.fieldOf("id").forGetter(TrackDefinition::id),
                Codec.FLOAT.fieldOf("duration").forGetter(TrackDefinition::duration),
                Identifier.CODEC.fieldOf("controller").forGetter(TrackDefinition::controller),
                Codec.STRING.optionalFieldOf("limited_by").forGetter(TrackDefinition::limitedBy)
            ).apply(instance, TrackDefinition::new)
        );
    }
}
