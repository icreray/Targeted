package io.creray.targeted.client;

import io.creray.targeted.client.animation.TrackController;
import io.creray.targeted.client.crosshair.rule.condition.RuleCondition;
import io.creray.targeted.util.ModIdentifier;
import lombok.experimental.UtilityClass;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

@UtilityClass
public class ModRegistryKeys {

    public final ResourceKey<Registry<TrackController>> TRACK_CONTROLLER;
    public final ResourceKey<Registry<RuleCondition>> SIMPLE_RULE_CONDITION;

    static {
        TRACK_CONTROLLER = createRegistryKey("track_controller");
        SIMPLE_RULE_CONDITION = createRegistryKey("simple_rule_condition");
    }

    private <T> ResourceKey<Registry<T>> createRegistryKey(String id) {
        return ResourceKey.createRegistryKey(ModIdentifier.of(id));
    }
}
