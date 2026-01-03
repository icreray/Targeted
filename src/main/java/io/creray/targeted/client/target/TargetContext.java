package io.creray.targeted.client.target;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public record TargetContext(
    @Nullable Entity entity,
    @Nullable BlockState blockState,
    @Nullable BlockPos blockPos,
    @Nullable ItemStack handItem
) {

    public static TargetContext of(Entity entity) {
        return new TargetContext(entity, null, null, null);
    }

    public static TargetContext of(BlockState state, BlockPos position) {
        return new TargetContext(null, state, position, null);
    }

    public static TargetContext empty() {
        return new TargetContext(null, null, null, null);
    }

    public LivingEntity livingEntity() {
        return (LivingEntity) entity;
    }
}
