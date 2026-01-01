package io.creray.targeted.client.resources;

import io.creray.targeted.client.crosshair.mode.ModeMap;
import io.creray.targeted.util.ModIdentifier;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;


public final class ModesLoader
    extends SimpleJsonResourceReloadListener<ModeDefinition>
{
    public static final Identifier IDENTIFIER = ModIdentifier.of("crosshair_mode");

    private final ModeMap modes;

    public ModesLoader(ModeMap modes) {
        super(ModeDefinition.CODEC, FileToIdConverter.json("crosshair/mode"));
        this.modes = modes;
    }

    @Override
    protected void apply(
        Map<Identifier, ModeDefinition> modeDefinitions,
        ResourceManager resourceManager,
        ProfilerFiller profilerFiller
    ) {
        modes.reset();
        modeDefinitions.forEach((id, def) -> {
            var mode = ModeCompiler.compile(def);
            modes.put(id, mode);
        });
    }
}
