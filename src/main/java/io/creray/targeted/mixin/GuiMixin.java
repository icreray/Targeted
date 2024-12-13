package io.creray.targeted.mixin;

import io.creray.targeted.Targeted;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;


@Mixin(Gui.class)
public final class GuiMixin {

    @Redirect(
            method = "Lnet/minecraft/client/gui/Gui;renderCrosshair(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V",
                    ordinal = 0
            )
    )
    private void ignoreVanillaCrosshairRender(
            GuiGraphics guiGraphics,
            Function<ResourceLocation, RenderType> renderType,
            ResourceLocation resourceLocation,
            int x, int y, int width, int height
    ) {}

    @Inject(
            method = "Lnet/minecraft/client/gui/Gui;renderCrosshair(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V",
                    ordinal = 0
            )
    )
    private void renderCrosshair(
            GuiGraphics guiGraphics,
            DeltaTracker deltaTracker,
            CallbackInfo callbackInfo
    ) {
        Targeted.crosshair.render(guiGraphics, deltaTracker);
    }

    private GuiMixin() {}
}
