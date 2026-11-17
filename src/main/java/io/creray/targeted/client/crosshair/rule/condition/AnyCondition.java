package io.creray.targeted.client.crosshair.rule.condition;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.creray.targeted.client.target.TargetContext;

import java.util.List;

public record AnyCondition(
    List<RuleCondition> conditions
) implements RuleCondition {

    public static final MapCodec<AnyCondition> CODEC;

    @Override
    public boolean matches(TargetContext context) {
        for (var condition : conditions) {
            if (condition.matches(context))
                return true;
        }
        return false;
    }

    @Override
    public Type type() {
        return RuleConditionTypes.ANY;
    }

    static {
        CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                RuleCondition.CODEC.listOf()
                    .fieldOf("conditions").forGetter(AnyCondition::conditions)
            ).apply(instance, AnyCondition::new)
        );
    }
}
