package io.creray.targeted.client.animation;

import io.creray.targeted.util.ModResource;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public record CrosshairSprite(ResourceLocation resourceLocation) {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;

    public static final CrosshairSprite DEFAULT = CrosshairSprite.from("hud/crosshair_default");
    public static final CrosshairSprite EMPTY = CrosshairSprite.from("hud/empty");

    public static final CrosshairSprite[] SHRINK = {
            CrosshairSprite.from("hud/crosshair_shrink_0"),
            CrosshairSprite.from("hud/crosshair_shrink_1"),
            CrosshairSprite.from("hud/crosshair_shrink_2")
    };

    public static final CrosshairSprite[] EXPAND = {
            CrosshairSprite.from("hud/crosshair_expand_0"),
            CrosshairSprite.from("hud/crosshair_expand_1"),
            CrosshairSprite.from("hud/crosshair_expand_2")
    };

    public static final CrosshairSprite[] HEALTH_INDICATOR = {
            EMPTY,
            CrosshairSprite.from("hud/health_indicator_0"),
            CrosshairSprite.from("hud/health_indicator_1"),
            CrosshairSprite.from("hud/health_indicator_2"),
            CrosshairSprite.from("hud/health_indicator_3"),
            CrosshairSprite.from("hud/health_indicator_4"),
            CrosshairSprite.from("hud/health_indicator_5"),
            CrosshairSprite.from("hud/health_indicator_6"),
            CrosshairSprite.from("hud/health_indicator_7")
    };

    public static CrosshairSprite from(String path) {
        return new CrosshairSprite(ModResource.of(path));
    }

    public void renderAtCenter(GuiGraphics guiGraphics) {
        var x = (guiGraphics.guiWidth() - WIDTH) / 2;
        var y = (guiGraphics.guiHeight() - HEIGHT) / 2;
        guiGraphics.blitSprite(resourceLocation, x, y, WIDTH, HEIGHT);
    }
}