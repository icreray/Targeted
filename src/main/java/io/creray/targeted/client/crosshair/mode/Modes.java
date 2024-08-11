package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.animation.*;

import java.util.Arrays;

public final class Modes {

    public static final Mode DEFAULT = sprite(CrosshairSprite.DEFAULT);
    public static final Mode HEALTH_INDICATOR = createHealthIndicator();
    public static final Mode CIRCLE = transition(3, CrosshairSprite.SHRINK);
    public static final Mode EXPANDED = transition(3, Arrays.copyOf(CrosshairSprite.SHRINK, 2));

    private static Mode sprite(CrosshairSprite sprite) {
        return Mode.builder()
                .addSprite(sprite)
                .build();
    }

    private static Mode transition(float duration, CrosshairSprite[] animation) {
        var transition = new Slider(duration);
        return Mode.builder()
                .addSlider(transition, Controllers.TRANSITION)
                .addAnimation(transition::getProgress, animation)
                .build();
    }


    private static Mode createHealthIndicator() {
        var transition = new Slider(8);
        var indicator = new Slider(6);

        return Mode.builder()
                .addSlider(transition, Controllers.TRANSITION)
                .addSlider(indicator, Controllers.HEALTH_PERCENT)
                .addAnimation(transition::getProgress, CrosshairSprite.HEALTH_INDICATOR)
                .addAnimation(() -> transition.limitedBy(indicator), CrosshairSprite.HEALTH_INDICATOR)
                .build();
    }

    private Modes() {}
}
