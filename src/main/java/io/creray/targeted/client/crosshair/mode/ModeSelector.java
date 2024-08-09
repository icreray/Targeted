package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.target.BlockTarget;
import io.creray.targeted.client.target.EntityTarget;
import io.creray.targeted.client.target.Target;
import io.creray.targeted.client.target.TargetContext;
import io.creray.targeted.util.BlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

public final class ModeSelector {

    public static Mode selectFor(Target target) {
        return switch (target) {
            case EntityTarget(var entity) ->
                    selectEntityMode(entity);
            case BlockTarget(var state, var position) ->
                    selectBlockMode(state, position);
            default ->
                    Modes.DEFAULT;
        };
    }

    private static Mode selectEntityMode(Entity entity) {
        if (entity instanceof LivingEntity) {
            return Modes.HEALTH_INDICATOR.with(TargetContext.of(entity));
        }
        return Modes.CIRCLE;
    }

    public static Mode selectBlockMode(BlockState state, BlockPos position) {
        if (BlockUtils.isWaxed(state.getBlock())) {
            return Modes.EXPANDED;
        }
        return Modes.DEFAULT;
    }

    private ModeSelector() {}
}
