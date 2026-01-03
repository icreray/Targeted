package io.creray.targeted.client.resources;

import io.creray.targeted.client.crosshair.mode.Mode;
import io.creray.targeted.util.ModIdentifier;
import io.creray.targeted.util.lang.ImmutableMaps;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Consumer;

public final class ModesLoader
    extends SimpleJsonResourceReloadListener<ModeDefinition>
{
    public static final Identifier ID = ModIdentifier.of("crosshair_mode");
    private final Consumer<Map<Identifier, Mode>> modesSetter;

    public ModesLoader(Consumer<Map<Identifier, Mode>> modesSetter) {
        super(ModeDefinition.CODEC, FileToIdConverter.json("crosshair/mode"));
        this.modesSetter = modesSetter;
    }

    @Override
    protected void apply(
        @NotNull Map<Identifier, ModeDefinition> modeDefinitions,
        @NotNull ResourceManager resourceManager,
        @NotNull ProfilerFiller profilerFiller
    ) {
        modesSetter.accept(ImmutableMaps.transformValues(
            modeDefinitions,
            ModeCompiler::compile
        ));
    }
}
