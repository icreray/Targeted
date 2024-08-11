package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.animation.Animation;
import io.creray.targeted.client.animation.CrosshairSprite;
import io.creray.targeted.client.animation.Slider;
import io.creray.targeted.client.animation.SliderController;

import java.util.ArrayList;
import java.util.function.Supplier;

public final class ModeBuilder {

    private final ArrayList<Slider> sliders;
    private final ArrayList<SliderController> controllers;
    private final ArrayList<Animation> animations;

    ModeBuilder() {
        sliders = new ArrayList<>();
        controllers = new ArrayList<>();
        animations = new ArrayList<>();
    }

    public ModeBuilder addSlider(Slider slider, SliderController controller) {
        sliders.add(slider);
        controllers.add(controller);
        return this;
    }

    public ModeBuilder addAnimation(Supplier<Float> progressSupplier, CrosshairSprite[] frames) {
        animations.add(new Animation(progressSupplier, frames));
        return this;
    }

    public ModeBuilder addSprite(CrosshairSprite sprite) {
        animations.add(new Animation(sprite));
        return this;
    }

    public Mode build() {
        return new Mode(
                sliders.toArray(new Slider[0]),
                controllers.toArray(new SliderController[0]),
                animations.toArray(new Animation[0])
        );
    }
}
