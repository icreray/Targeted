package io.creray.targeted.client.animation;

import io.creray.targeted.client.target.TargetContext;
import io.creray.targeted.util.EntityUtils;

import java.util.Map;

public final class Controllers {

    public static final SliderController HEALTH_PERCENT = (slider, context) ->
            slider.runTowards(EntityUtils.healthPercent(context.livingEntity()));

    public static final SliderController TRANSITION = new SliderController() {
        @Override
        public void enable(Slider slider) {
            slider.runForward();
        }

        @Override
        public void updateWith(Slider slider, TargetContext context) {}

        @Override
        public void disable(Slider slider) {
            slider.runBackward();
        }
    };

    private static final Map<String, SliderController> BY_KEY = Map.of(
            "health_percent", HEALTH_PERCENT,
            "transition_progress", TRANSITION
    );

    public static SliderController get(String key) {
        return BY_KEY.get(key);
    }

    private Controllers() {}
}
