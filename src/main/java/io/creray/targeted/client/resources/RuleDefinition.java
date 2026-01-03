package io.creray.targeted.client.resources;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.client.crosshair.rule.ModeTrigger;
import io.creray.targeted.client.crosshair.rule.condition.RuleCondition;
import net.minecraft.resources.Identifier;

import java.util.List;

public record RuleDefinition(
    int priority,
    ModeTrigger trigger,
    List<RuleCondition> conditions,
    Identifier mode
) {
    public static final Codec<RuleDefinition> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            Codec.INT.fieldOf("priority").forGetter(RuleDefinition::priority),
            ModRegistries.MODE_TRIGGER.byNameCodec().fieldOf("trigger").forGetter(RuleDefinition::trigger),
            RuleCondition.CODEC.listOf().fieldOf("conditions").forGetter(RuleDefinition::conditions),
            Identifier.CODEC.fieldOf("mode").forGetter(RuleDefinition::mode)
        ).apply(instance, RuleDefinition::new)
    );
}
