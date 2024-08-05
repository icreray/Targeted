package io.creray.targeted.client;

import io.creray.targeted.util.EntityUtils;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public final class Crosshair {

    private final Minecraft client;
    @Nullable
    private LivingEntity target;

    private final Animation expandAnimation = new Animation(8);
    private final Animation indicatorAnimation = new Animation(8);

    public Crosshair(Minecraft client) {
        this.client = client;
        this.target = null;
    }

    public void tick() {
        if (client.crosshairPickEntity instanceof LivingEntity newTarget) {
            updateTarget(newTarget);
            expandAnimation.forward();
            return;
        }

        updateTarget(null);
        expandAnimation.backward();
        indicatorAnimation.pause();
    }

    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        updateAnimations(deltaTracker);
        int frame = (int)(expandAnimation.time() * 3F/8F);
        CrosshairSprite.EXPAND[frame].renderAtCenter(guiGraphics);
        frame = (int)Math.min(expandAnimation.time(), indicatorAnimation.time());
        if (frame != 0) {
            CrosshairSprite.HEALTH_INDICATOR[frame - 1].renderAtCenter(guiGraphics);
        }
    }

    private void updateTarget(LivingEntity newTarget) {
        if (target == newTarget) return;
        target = newTarget;
    }

    private void updateAnimations(DeltaTracker deltaTracker) {
        float delta = deltaTracker.getGameTimeDeltaTicks();

        if (target != null) {
            int healthPercent = (int) (EntityUtils.healthPercent(target) * 8F);
            float time = indicatorAnimation.time();

            if (time > healthPercent) {
                indicatorAnimation.setRange(healthPercent, 8);
                indicatorAnimation.backward();
            }
            else if (time < healthPercent) {
                indicatorAnimation.setRange(0, healthPercent);
                indicatorAnimation.forward();
            }
        }

        expandAnimation.update(delta);
        indicatorAnimation.update(delta);
    }
}
