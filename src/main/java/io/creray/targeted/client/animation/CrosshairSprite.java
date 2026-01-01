package io.creray.targeted.client.animation;

import io.creray.targeted.util.ModIdentifier;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public record CrosshairSprite(Identifier resourceLocation) {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;

    public static final CrosshairSprite DEFAULT = CrosshairSprite.from("crosshair/default");

    public static CrosshairSprite from(String path) {
        return new CrosshairSprite(ModIdentifier.of(path));
    }

    public void renderAtCenter(GuiGraphics guiGraphics) {
        var x = (guiGraphics.guiWidth() - WIDTH) / 2;
        var y = (guiGraphics.guiHeight() - HEIGHT) / 2;
        guiGraphics.blitSprite(RenderPipelines.CROSSHAIR, resourceLocation, x, y, WIDTH, HEIGHT);
    }
}
