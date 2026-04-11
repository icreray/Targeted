package io.creray.targeted.mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import io.creray.targeted.Targeted;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Gui.class)
public final class GuiMixin {

    @SuppressWarnings("EmptyMethod")
    @Redirect(
            method = "extractCrosshair(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
                    ordinal = 0
            )
    )
    private void ignoreVanillaExtractCrosshair(
            GuiGraphicsExtractor guiGraphics,
            RenderPipeline renderPipeline,
            Identifier location,
            int x, int y, int width, int height
    ) {}

    @Inject(
            method = "extractCrosshair(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
                    ordinal = 0
            )
    )
    private void extractCrosshair(
            GuiGraphicsExtractor graphics,
            DeltaTracker deltaTracker,
            CallbackInfo callbackInfo
    ) {
        Targeted.crosshair.extract(graphics, deltaTracker);
    }

    private GuiMixin() {}
}
