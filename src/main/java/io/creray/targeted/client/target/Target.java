package io.creray.targeted.client.target;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public sealed interface Target
        permits EntityTarget, BlockTarget, EmptyTarget {

    static Target entity(Entity entity) {
        return new EntityTarget(entity);
    }

    static Target block(BlockState block, BlockPos position) {
        return new BlockTarget(block, position);
    }

    static Target empty() {
        return EmptyTarget.INSTANCE;
    }

    static Target extractFrom(Minecraft client) {
        assert client.hitResult != null;

        var hit = client.hitResult;

        return switch (hit.getType()) {
            case ENTITY ->
                    entity(((EntityHitResult)hit).getEntity());
            case BLOCK -> {
                assert client.level != null;

                var position = ((BlockHitResult)hit).getBlockPos();
                var block = client.level.getBlockState(position);
                yield block(block, position);
            }
            default -> empty();
        };
    }
}
