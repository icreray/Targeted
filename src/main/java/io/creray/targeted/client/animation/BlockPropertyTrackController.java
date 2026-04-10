package io.creray.targeted.client.animation;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import io.creray.targeted.client.target.TargetContext;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

public record BlockPropertyTrackController<T extends Comparable<T>>(
    Block block,
    Property<@NotNull T> property
) implements TrackController {

    public static final MapCodec<BlockPropertyTrackController<?>> CODEC;

    @Override
    public void updateWith(Track track, TargetContext context) {
        var blockState = context.blockState();
        assert blockState != null;
        if (!blockState.is(block)) return;

        var propertyValue = blockState.getValue(property);
        float propertyIndex = property.getInternalIndex(propertyValue);
        float maxIndex = property.getPossibleValues().size() - 1;
        track.runTowards(propertyIndex / maxIndex);
    }

    @Override
    public Type type() {
        return TrackControllerTypes.BLOCK_PROPERTY;
    }

    static {
        CODEC = Codec.mapPair(
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block"),
            Codec.STRING.fieldOf("property")
        ).flatXmap(
            pair -> create(pair.getFirst(), pair.getSecond()),
            controller -> DataResult.success(Pair.of(controller.block, controller.property.getName()))
        );
    }

    private static DataResult<BlockPropertyTrackController<?>> create(Block block, String propertyName) {
        var property = block.getStateDefinition()
            .getProperty(propertyName);
        if (property == null)
            return DataResult.error(() -> "Block " + block + " has no property named " + propertyName);
        return DataResult.success(new BlockPropertyTrackController<>(block, property));
    }
}
