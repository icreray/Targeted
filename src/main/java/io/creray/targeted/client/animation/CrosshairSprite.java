package io.creray.targeted.client.animation;

import io.creray.targeted.util.ModIdentifier;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public record CrosshairSprite(Identifier resourceLocation) {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;

    public static final CrosshairSprite EMPTY = CrosshairSprite.from("crosshair/empty");
    public static final CrosshairSprite DEFAULT = CrosshairSprite.from("crosshair/default");

    public static final CrosshairSprite[] SHRINK = {
            CrosshairSprite.from("crosshair/shrink_0"),
            CrosshairSprite.from("crosshair/shrink_1"),
            CrosshairSprite.from("crosshair/shrink_2")
    };

    public static final CrosshairSprite[] EXPAND = {
            CrosshairSprite.from("crosshair/expand_0"),
            CrosshairSprite.from("crosshair/expand_1"),
            CrosshairSprite.from("crosshair/expand_2")
    };

    public static final CrosshairSprite[] HEALTH_INDICATOR = {
            EMPTY,
            CrosshairSprite.from("crosshair/health_indicator_0"),
            CrosshairSprite.from("crosshair/health_indicator_1"),
            CrosshairSprite.from("crosshair/health_indicator_2"),
            CrosshairSprite.from("crosshair/health_indicator_3"),
            CrosshairSprite.from("crosshair/health_indicator_4"),
            CrosshairSprite.from("crosshair/health_indicator_5"),
            CrosshairSprite.from("crosshair/health_indicator_6"),
            CrosshairSprite.from("crosshair/health_indicator_7")
    };

    public static CrosshairSprite from(String path) {
        return new CrosshairSprite(ModIdentifier.of(path));
    }

    public void renderAtCenter(GuiGraphics guiGraphics) {
        var x = (guiGraphics.guiWidth() - WIDTH) / 2;
        var y = (guiGraphics.guiHeight() - HEIGHT) / 2;
        guiGraphics.blitSprite(RenderPipelines.CROSSHAIR, resourceLocation, x, y, WIDTH, HEIGHT);
    }
}
