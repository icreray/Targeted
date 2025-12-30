package io.creray.targeted.client.crosshair;

import io.creray.targeted.client.crosshair.mode.Mode;
import io.creray.targeted.client.crosshair.mode.Modes;
import io.creray.targeted.client.Target;
import io.creray.targeted.util.BlockUtils;
import io.creray.targeted.util.EntityUtils;
import lombok.experimental.UtilityClass;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

@UtilityClass
public class ModeSelector {

    public Mode selectFor(Target target) {
        return switch (target) {
            case Target.Entity(var entity) ->
                selectEntityMode(entity);
            case Target.Block(var state, var position) ->
                selectBlockMode(state, position);
            default ->
                Modes.DEFAULT;
        };
    }

    private Mode selectEntityMode(Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            return Modes.HEALTH_INDICATOR.with(() -> EntityUtils.healthPercent(livingEntity));
        }
        return Modes.CIRCLE;
    }

    private Mode selectBlockMode(BlockState state, BlockPos ignoredPosition) {
        if (BlockUtils.isWaxed(state.getBlock())) {
            return Modes.EXPANDED;
        }
        return Modes.DEFAULT;
    }
}
