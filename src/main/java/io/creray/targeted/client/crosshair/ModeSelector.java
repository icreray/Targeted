package io.creray.targeted.client.crosshair;

import io.creray.targeted.Targeted;
import io.creray.targeted.client.crosshair.mode.Mode;
import io.creray.targeted.client.crosshair.mode.ModeIds;
import io.creray.targeted.client.crosshair.mode.ModeMap;
import io.creray.targeted.client.target.Target;
import io.creray.targeted.client.target.TargetContext;
import io.creray.targeted.util.BlockUtils;
import lombok.experimental.UtilityClass;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

// TODO: Replace with data-driven selector
@Deprecated
@UtilityClass
public class ModeSelector {

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
            return Targeted.modes
                .get(ModeIds.HEALTH_INDICATOR)
                .with(TargetContext.of(entity));
        }
        return Targeted.modes.get(ModeIds.CIRCLE);
    }

    private Mode selectBlockMode(BlockState state, BlockPos ignoredPosition) {
        if (BlockUtils.isWaxed(state.getBlock())) {
            return Targeted.modes.get(ModeIds.EXPANDED);
        }
        return ModeMap.DEFAULT_MODE;
    }
}
