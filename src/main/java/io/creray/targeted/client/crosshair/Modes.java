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

    public static final Mode CIRCLE = new SimpleMode(
            3, new SpriteAnimation(CrosshairSprite.SHRINK)
    );

    public static final Mode EXPANDED = new SimpleMode(
            3, new SpriteAnimation(
                    CrosshairSprite.EXPAND[0],
                    CrosshairSprite.EXPAND[1]
            )
    );

    private Modes() {}
}
