package io.creray.targeted.util;

import net.minecraft.world.entity.LivingEntity;

public final class EntityUtils {

    public static float healthPercent(LivingEntity entity) {
        return entity.getHealth() / entity.getMaxHealth();
    }

    private EntityUtils() {}
}
