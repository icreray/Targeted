package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.animation.*;

public final class Modes {

    public static final Mode DEFAULT = ModeFactory.singleSprite(CrosshairSprite.DEFAULT);

    public static final Mode HEALTH_INDICATOR = createHealthIndicator();

    public static final Mode CIRCLE = ModeFactory.simpleTransition(3, CrosshairSprite.SHRINK);

    public static final Mode EXPANDED = ModeFactory.simpleTransition(
            3, new CrosshairSprite[]{ CrosshairSprite.EXPAND[0], CrosshairSprite.EXPAND[1]}
    );


    private static Mode createHealthIndicator() {
        var transition = new Slider(8);
        var indicator = new Slider(6);

        return new Mode(
                new Slider[] {transition, indicator},
                new SliderController[] {Controllers.TRANSITION, Controllers.HEALTH_PERCENT},
                new Animation[] {
                        new Animation(transition::getProgress, CrosshairSprite.EXPAND),
                        new Animation(() -> transition.limitedBy(indicator), CrosshairSprite.HEALTH_INDICATOR)
                }
        );
    }

    private Modes() {}
}
