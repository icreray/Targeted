package io.creray.targeted.client.animation;

import net.minecraft.client.gui.GuiGraphics;

import java.util.function.Supplier;

public final class Animation {

    private final CrosshairSprite[] FRAMES;
    private final Supplier<Float> progressSupplier;

    public Animation(CrosshairSprite ... frames) {
        this(() -> 1.0f, frames);
    }

    public Animation(Supplier<Float> progressSupplier, CrosshairSprite ... frames) {
        this.progressSupplier = progressSupplier;
        FRAMES = frames;
    }

    public void renderFrame(GuiGraphics guiGraphics) {
        getFrame(progressSupplier.get()).renderAtCenter(guiGraphics);
    }

    public CrosshairSprite getFrame(float progress) {
        assert progress >= 0 && progress <= 1;
        int frame = (int)(progress * (FRAMES.length - 1));
        return FRAMES[frame];
    }
}
