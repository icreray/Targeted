package io.creray.targeted.client.crosshair.rule.condition;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.creray.targeted.client.target.TargetContext;

import java.util.List;

public record AllCondition(
    List<RuleCondition> conditions
) implements RuleCondition {

    public static final MapCodec<AllCondition> CODEC;

    @Override
    public boolean matches(TargetContext context) {
        for (var condition : conditions) {
            if (!condition.matches(context))
                return false;
        }
        return true;
    }

    @Override
    public Type type() {
        return RuleConditionTypes.ALL;
    }

    static {
        CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                RuleCondition.CODEC.listOf()
                    .fieldOf("conditions").forGetter(AllCondition::conditions)
            ).apply(instance, AllCondition::new)
        );
    }
}
