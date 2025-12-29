package io.creray.targeted.util;

import lombok.experimental.UtilityClass;
import net.minecraft.world.entity.LivingEntity;

@UtilityClass
public class EntityUtils {

    public float healthPercent(LivingEntity entity) {
        return entity.getHealth() / entity.getMaxHealth();
    }
}
