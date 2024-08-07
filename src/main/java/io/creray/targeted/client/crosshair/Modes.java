package io.creray.targeted.client.crosshair;

import io.creray.targeted.client.animation.CrosshairSprite;
import io.creray.targeted.client.animation.SpriteAnimation;

public final class Modes {

    public static final Mode DEFAULT = (guiRenderer, deltaTracker) ->
            CrosshairSprite.DEFAULT.renderAtCenter(guiRenderer);

    public static final IndicatorMode HEALTH_INDICATOR = new IndicatorMode(
            6, 8,
            new SpriteAnimation(CrosshairSprite.EXPAND),
            new SpriteAnimation(CrosshairSprite.HEALTH_INDICATOR)
    );

    public static final Mode DOT_MODE = new SimpleMode(
            3, new SpriteAnimation(CrosshairSprite.SHRINK)
    );

    private Modes() {}
}
