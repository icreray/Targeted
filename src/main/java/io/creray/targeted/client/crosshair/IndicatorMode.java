package io.creray.targeted.client.crosshair;

import io.creray.targeted.client.animation.AnimationProgress;
import io.creray.targeted.client.animation.SpriteAnimation;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;

import java.util.function.Supplier;

public final class IndicatorMode extends SimpleMode {

    private Supplier<Float> valueSupplier;
    private float value;

    private final AnimationProgress indicator;
    private final SpriteAnimation indicatorAnimation;

    {
        valueSupplier = () -> 0.0f;
        value = 0;
    }

    public IndicatorMode(
            float transitionDuration,
            float indicatorSpeed,
            SpriteAnimation transitionAnimation,
            SpriteAnimation indicatorAnimation
    ) {
        super(transitionDuration, transitionAnimation);
        this.indicator = new AnimationProgress(indicatorSpeed);
        this.indicatorAnimation = indicatorAnimation;
    }

    public Mode with(Supplier<Float> valueSupplier) {
        this.valueSupplier = valueSupplier;
        return this;
    }

    @Override
    public void tick() {
        value = valueSupplier.get();
        indicator.runTowards(value);
    }

    @Override
    public void render(GuiGraphics guiRenderer, DeltaTracker deltaTracker) {
        super.render(guiRenderer, deltaTracker);

        indicator.update(deltaTracker.getGameTimeDeltaTicks());
        indicatorAnimation
                .getFrame(indicator.getMinProgress(transition))
                .renderAtCenter(guiRenderer);
    }
}
