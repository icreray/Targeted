package io.creray.targeted.mixin;

import io.creray.targeted.Targeted;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.At.Shift;

@Mixin(GameRenderer.class)
public final class GameRendererMixin {

    @Inject(
            method = "Lnet/minecraft/client/renderer/GameRenderer;pick(F)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V",
                    shift = Shift.AFTER
            )
    )
    private void afterCrosshairPick(float tickDelta, CallbackInfo callback) {
        Targeted.crosshair.tick();
    }

    private GameRendererMixin() {}
}
