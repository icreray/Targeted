package io.creray.targeted.client.animation;

public final class SpriteAnimation {

    private final CrosshairSprite[] FRAMES;

    public SpriteAnimation(CrosshairSprite ... frames) {
        FRAMES = frames;
    }

    public CrosshairSprite getFrame(AnimationProgress progress) {
        return getFrame(progress.getProgress());
    }

    public CrosshairSprite getFrame(float progress) {
        assert progress >= 0 && progress <= 1;
        int frame = (int)(progress * (FRAMES.length - 1));
        return FRAMES[frame];
    }
}
