package io.creray.targeted.client.target;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public sealed interface Target
    permits Target.Empty, Target.Entity, Target.Block {

    @SuppressWarnings("SameReturnValue")
    static Target empty() {
        return Empty.INSTANCE;
    }

    static Target block(BlockState block, BlockPos position) {
        return new Block(block, position);
    }

    static Target entity(net.minecraft.world.entity.Entity entity) {
        return new Entity(entity);
    }

    static Target extractFrom(Minecraft client) {
        assert client.hitResult != null;

        var hit = client.hitResult;

        return switch (hit) {
            case EntityHitResult entityHit ->
                entity(entityHit.getEntity());
            case BlockHitResult blockHit -> {
                assert client.level != null;
                var position = blockHit.getBlockPos();
                var block = client.level.getBlockState(position);
                yield block(block, position);
            }
            default -> empty();
        };
    }

    final class Empty implements Target {
        public static final Empty INSTANCE = new Empty();
        private Empty() {}
    }

    record Block(
        BlockState state,
        BlockPos position
    ) implements Target {}

    record Entity(
        net.minecraft.world.entity.Entity entity
    ) implements Target {}
}
