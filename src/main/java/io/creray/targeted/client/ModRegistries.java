package io.creray.targeted.client;

import io.creray.targeted.client.animation.TrackController;
import io.creray.targeted.client.animation.SimpleTrackControllers;
import io.creray.targeted.client.animation.TrackControllerTypes;
import io.creray.targeted.client.crosshair.rule.ModeTrigger;
import io.creray.targeted.client.crosshair.rule.ModeTriggers;
import io.creray.targeted.client.crosshair.rule.condition.RuleCondition;
import io.creray.targeted.client.crosshair.rule.condition.RuleConditionTypes;
import io.creray.targeted.client.crosshair.rule.condition.SimpleRuleConditions;
import lombok.experimental.UtilityClass;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

@UtilityClass
public class ModRegistries {

    public final Registry<TrackController> SIMPLE_TRACK_CONTROLLER;
    public final Registry<TrackController.Type> TRACK_CONTROLLER_TYPE;
    public final Registry<RuleCondition> SIMPLE_RULE_CONDITION;
    public final Registry<RuleCondition.Type> RULE_CONDITION_TYPE;
    public final Registry<ModeTrigger> MODE_TRIGGER;

    static {
        SIMPLE_TRACK_CONTROLLER = registerSimple(ModRegistryKeys.SIMPLE_TRACK_CONTROLLER);
        TRACK_CONTROLLER_TYPE = registerSimple(ModRegistryKeys.TRACK_CONTROLLER_TYPE);
        SIMPLE_RULE_CONDITION = registerSimple(ModRegistryKeys.SIMPLE_RULE_CONDITION);
        RULE_CONDITION_TYPE = registerSimple(ModRegistryKeys.RULE_CONDITION_TYPE);
        MODE_TRIGGER = registerSimple(ModRegistryKeys.MODE_TRIGGER);
    }

    public void registerAll() {
        SimpleTrackControllers.registerAll();
        TrackControllerTypes.registerAll();
        SimpleRuleConditions.registerAll();
        RuleConditionTypes.registerAll();
        ModeTriggers.registerAll();
    }

    private <T> Registry<T> registerSimple(ResourceKey<Registry<T>> registryKey) {
        return FabricRegistryBuilder.createSimple(registryKey).buildAndRegister();
    }
}
