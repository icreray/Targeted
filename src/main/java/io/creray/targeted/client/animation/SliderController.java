package io.creray.targeted.client.animation;

import io.creray.targeted.client.target.TargetContext;

@FunctionalInterface
public interface SliderController {

    default void enable(Slider slider) {}

    void updateWith(Slider slider, TargetContext context);

    default void disable(Slider slider) {}

}
