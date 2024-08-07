package io.creray.targeted.util;

import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.Block;

public final class BlockUtils {

    public static boolean isWaxed(Block block) {
        return HoneycombItem.WAXABLES.get().containsValue(block);
    }

    private BlockUtils() {}
}
