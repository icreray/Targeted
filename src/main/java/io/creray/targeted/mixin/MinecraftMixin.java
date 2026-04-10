package io.creray.targeted.mixin;

import io.creray.targeted.Targeted;
import io.creray.targeted.client.target.Target;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.At.Shift;

@Mixin(Minecraft.class)
public final class MinecraftMixin {

    @Inject(
            method = "pick(F)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V",
                    shift = Shift.AFTER
            )
    )
    private void afterCrosshairPick(float tickDelta, CallbackInfo callback) {
        Targeted.crosshair.updateTarget(Target.extractFrom((Minecraft)(Object)this));
        Targeted.crosshair.tick();
    }

    private MinecraftMixin() {}
}
