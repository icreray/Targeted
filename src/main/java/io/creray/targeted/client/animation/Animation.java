package io.creray.targeted.client.animation;

import net.minecraft.client.gui.GuiGraphics;

public final class Animation {

    private final CrosshairSprite[] FRAMES;
    private final Track.Driver trackDriver;

    public Animation(CrosshairSprite ... frames) {
        this(Track.Driver.STATIC, frames);
    }

    public Animation(Track.Driver trackDriver, CrosshairSprite ... frames) {
        this.trackDriver = trackDriver;
        this.FRAMES = frames;
    }

    public void renderFrame(GuiGraphics guiGraphics) {
        getFrame(trackDriver.get()).renderAtCenter(guiGraphics);
    }

    public CrosshairSprite getFrame(Track progress) {
        return getFrame(progress.get());
    }

    public CrosshairSprite getFrame(float progress) {
        assert progress >= 0 && progress <= 1;
        int frame = (int)(progress * (FRAMES.length - 1));
        return FRAMES[frame];
    }
}
