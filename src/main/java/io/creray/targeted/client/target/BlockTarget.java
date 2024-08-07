package io.creray.targeted.client.target;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public record BlockTarget(
        BlockState state,
        BlockPos position
) implements Target {

    @Override
    public boolean equals(Object object) {
        return object instanceof BlockTarget(var otherState, var otherPosition) &&
                this.state.getBlock() == otherState.getBlock() &&
                this.position.equals(otherPosition);
    }
}
