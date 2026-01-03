package io.creray.targeted.client.resources;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.client.animation.TrackController;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.VisibleForTesting;

import static com.mojang.serialization.codecs.RecordCodecBuilder.Instance;

import java.util.List;
import java.util.Optional;

public record ModeDefinition(
    List<AnimationDefinition> animations
) {
    public static final Codec<ModeDefinition> CODEC = RecordCodecBuilder.create(
        (Instance<ModeDefinition> instance) -> instance.group(
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
        TrackController controller,
        Optional<String> limitedBy
    ) {
        public static final Codec<TrackDefinition> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                Codec.STRING.fieldOf("id").forGetter(TrackDefinition::id),
                Codec.floatRange(0, 100).fieldOf("duration").forGetter(TrackDefinition::duration),
                ModRegistries.TRACK_CONTROLLER.byNameCodec().fieldOf("controller").forGetter(TrackDefinition::controller),
                Codec.STRING.optionalFieldOf("limited_by").forGetter(TrackDefinition::limitedBy)
            ).apply(instance, TrackDefinition::new)
        );
    }
}
