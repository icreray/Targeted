package io.creray.targeted.client;

import io.creray.targeted.client.animation.TrackController;
import io.creray.targeted.client.crosshair.rule.ModeTrigger;
import io.creray.targeted.client.crosshair.rule.condition.RuleCondition;
import io.creray.targeted.util.ModIdentifier;
import lombok.experimental.UtilityClass;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

@UtilityClass
public class ModRegistryKeys {

    /** @see io.creray.targeted.client.animation.SimpleTrackControllers */
    public final ResourceKey<Registry<TrackController>> SIMPLE_TRACK_CONTROLLER;
    /** @see io.creray.targeted.client.animation.TrackControllerTypes */
    public final ResourceKey<Registry<TrackController.Type>> TRACK_CONTROLLER_TYPE;
    /** @see io.creray.targeted.client.crosshair.rule.condition.RuleConditionTypes */
    public final ResourceKey<Registry<RuleCondition.Type>> RULE_CONDITION_TYPE;
    /** @see io.creray.targeted.client.crosshair.rule.condition.SimpleRuleConditions */
    public final ResourceKey<Registry<RuleCondition>> SIMPLE_RULE_CONDITION;
    /** @see io.creray.targeted.client.crosshair.rule.ModeTriggers */
    public final ResourceKey<Registry<ModeTrigger>> MODE_TRIGGER;

    static {
        SIMPLE_TRACK_CONTROLLER = createRegistryKey("simple_track_controller");
        TRACK_CONTROLLER_TYPE = createRegistryKey("track_controller_type");
        RULE_CONDITION_TYPE = createRegistryKey("rule_condition_type");
        SIMPLE_RULE_CONDITION = createRegistryKey("simple_rule_condition");
        MODE_TRIGGER = createRegistryKey("rule_trigger");
    }

    private <T> ResourceKey<Registry<T>> createRegistryKey(String id) {
        return ResourceKey.createRegistryKey(ModIdentifier.of(id));
    }
}
