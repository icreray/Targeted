package io.creray.targeted.client.animation;

import com.mojang.serialization.MapCodec;
import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.util.ModIdentifier;
import lombok.experimental.UtilityClass;
import net.minecraft.core.Registry;

@UtilityClass
public class TrackControllerTypes {

    public final TrackController.Type SIMPLE;
    public final TrackController.Type BLOCK_PROPERTY;

    static {
        SIMPLE = register("simple", ModRegistries.SIMPLE_TRACK_CONTROLLER.byNameCodec().fieldOf("id"));
        BLOCK_PROPERTY = register("block_property", BlockPropertyTrackController.CODEC);
    }

    @SuppressWarnings("EmptyMethod")
    public void registerAll() {
        // Initialize static constructor
    }

    private TrackController.Type register(String path, MapCodec<? extends TrackController> codec) {
        return Registry.register(
            ModRegistries.TRACK_CONTROLLER_TYPE,
            ModIdentifier.of(path),
            new TrackController.Type(codec)
        );
    }
}
