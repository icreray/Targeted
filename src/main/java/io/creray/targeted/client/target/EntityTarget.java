package io.creray.targeted.client.target;

import net.minecraft.world.entity.Entity;

public record EntityTarget(
        Entity entity
) implements Target {

    @Override
    public boolean equals(Object object) {
        return object instanceof EntityTarget(var otherEntity) &&
                this.entity == otherEntity;
    }
}
