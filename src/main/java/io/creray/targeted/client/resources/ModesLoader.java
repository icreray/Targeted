package io.creray.targeted.client.resources;

import com.mojang.logging.LogUtils;
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
    public static final Identifier IDENTIFIER = ModIdentifier.of("crosshair_modes");

    private final ModeMap modes;

    public ModesLoader(ModeMap modes) {
        super(ModeDefinition.CODEC, FileToIdConverter.json("crosshair/mode"));
        LogUtils.getLogger().info("Creating ModesLoader");
        this.modes = modes;
    }

    @Override
    protected void apply(
        Map<Identifier, ModeDefinition> modeDefinitions,
        ResourceManager resourceManager,
        ProfilerFiller profilerFiller
    ) {
        modes.reset();
        LogUtils.getLogger().info("Applying ModeLoader");
        modeDefinitions.forEach((id, def) -> {
            var mode = ModeCompiler.compile(def);
            LogUtils.getLogger().info("Compiled {}", id);
            modes.put(id, mode);
        });
    }
}
