package io.creray.targeted.util;

import lombok.experimental.UtilityClass;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

@UtilityClass
public class BlockUtils {

    public boolean isWaxed(Block block) {
        return HoneycombItem.WAXABLES.get().containsValue(block);
    }

    public boolean isWaxed(BlockState blockState) {
        return isWaxed(blockState.getBlock());
    }
}
