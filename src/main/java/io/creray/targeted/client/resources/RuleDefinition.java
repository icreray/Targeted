package io.creray.targeted.client.resources;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;

import java.util.List;

public record RuleDefinition(
    int priority,
    Identifier trigger, // TODO: Make registry?
    List<Identifier> conditions, // TODO: Make complex conditions
    Identifier mode
) {
    public static final Codec<RuleDefinition> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            Codec.INT.fieldOf("priority").forGetter(RuleDefinition::priority),
            Identifier.CODEC.fieldOf("trigger").forGetter(RuleDefinition::trigger),
            Identifier.CODEC.listOf().fieldOf("conditions").forGetter(RuleDefinition::conditions),
            Identifier.CODEC.fieldOf("mode").forGetter(RuleDefinition::mode)
        ).apply(instance, RuleDefinition::new)
    );
}
