package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.animation.*;

public final class ModeFactory {

    public static Mode singleSprite(CrosshairSprite sprite) {
        return new Mode(
                new Slider[] {},
                new SliderController[] {},
                new Animation[] {
                    new Animation(sprite)
                }
        );
    }

    public static Mode simpleTransition(float duration, CrosshairSprite[] frames) {
        var transition = new Slider(duration);
        return new Mode(
                new Slider[] {transition},
                new SliderController[] {Controllers.TRANSITION},
                new Animation[] {
                        new Animation(transition::getProgress, frames),
                }
        );
    }

    private ModeFactory() {}
}
