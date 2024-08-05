package io.creray.targeted.mixin;

import io.creray.targeted.client.Crosshair;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public final class GuiMixin {

    @Unique
    private final Crosshair crosshair;

    {
        crosshair = new Crosshair(Minecraft.getInstance());
    }

    @Redirect(
            method = "Lnet/minecraft/client/gui/Gui;renderCrosshair(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V",
                    ordinal = 0
            )
    )
    private void ignoreVanillaCrosshairRender(
            GuiGraphics guiGraphics,
            ResourceLocation resourceLocation,
            int x, int y, int width, int height
    ) {}

    @Inject(
            method = "Lnet/minecraft/client/gui/Gui;renderCrosshair(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V",
                    ordinal = 0
            )
    )
    private void renderCrosshair(
            GuiGraphics guiGraphics,
            DeltaTracker deltaTracker,
            CallbackInfo callbackInfo
    ) {
        crosshair.render(guiGraphics, deltaTracker);
    }

    @Inject(
            method = "Lnet/minecraft/client/gui/Gui;tick()V",
            at = @At("TAIL")
    )
    private void onTick(CallbackInfo callback) {
        crosshair.tick();
    }

    private GuiMixin() {}
}
