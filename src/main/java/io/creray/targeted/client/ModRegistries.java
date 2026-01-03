package io.creray.targeted.client;

import io.creray.targeted.client.animation.TrackController;
import io.creray.targeted.client.animation.TrackControllers;
import io.creray.targeted.client.crosshair.rule.ModeTrigger;
import io.creray.targeted.client.crosshair.rule.ModeTriggers;
import io.creray.targeted.client.crosshair.rule.condition.RuleCondition;
import io.creray.targeted.client.crosshair.rule.condition.SimpleRuleConditions;
import lombok.experimental.UtilityClass;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.core.Registry;

@UtilityClass
public class ModRegistries {

    public final Registry<TrackController> TRACK_CONTROLLER;
    public final Registry<RuleCondition> SIMPLE_RULE_CONDITION;
    public final Registry<ModeTrigger> MODE_TRIGGER;

    static {
        TRACK_CONTROLLER = FabricRegistryBuilder
            .createSimple(ModRegistryKeys.TRACK_CONTROLLER)
            .buildAndRegister();
        SIMPLE_RULE_CONDITION = FabricRegistryBuilder
            .createSimple(ModRegistryKeys.SIMPLE_RULE_CONDITION)
            .buildAndRegister();
        MODE_TRIGGER = FabricRegistryBuilder
            .createSimple(ModRegistryKeys.MODE_TRIGGER)
            .buildAndRegister();
    }

    public void registerAll() {
        TrackControllers.registerAll();
        SimpleRuleConditions.registerAll();
        ModeTriggers.registerAll();
    }
}
