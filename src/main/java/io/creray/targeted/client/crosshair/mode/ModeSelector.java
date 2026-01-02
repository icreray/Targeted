package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.resources.ModesLoader;
import io.creray.targeted.client.target.Target;
import io.creray.targeted.client.target.TargetContext;
import io.creray.targeted.util.BlockUtils;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public final class ModeSelector {

    private ModeMap modes;

    {
        this.modes = ModeMap.EMPTY;
    }

    public Mode selectFor(Target target) {
        return switch (target) {
            case Target.Entity(var entity) ->
                selectEntityMode(entity);
            case Target.Block(var state, var position) ->
                selectBlockMode(state, position);
            default ->
                ModeMap.DEFAULT_MODE;
        };
    }

    private Mode selectEntityMode(Entity entity) {
        if (entity instanceof LivingEntity) {
            return modes
                .get(ModeIds.HEALTH_INDICATOR)
                .with(TargetContext.of(entity));
        }
        return modes.get(ModeIds.CIRCLE);
    }

    private Mode selectBlockMode(BlockState state, BlockPos ignoredPosition) {
        if (BlockUtils.isWaxed(state.getBlock())) {
            return modes.get(ModeIds.EXPANDED);
        }
        return ModeMap.DEFAULT_MODE;
    }

    public void registerResourceReloaders(ResourceLoader loader) {
        loader.registerReloader(ModesLoader.IDENTIFIER, new ModesLoader(this::setLoadedModes));
    }

    private void setLoadedModes(Map<Identifier, Mode> loadedModes) {
        modes = new ModeMap(loadedModes);
    }
}
