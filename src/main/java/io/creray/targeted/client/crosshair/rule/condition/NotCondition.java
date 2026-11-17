package io.creray.targeted.client.crosshair.rule.condition;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.creray.targeted.client.target.TargetContext;

public record NotCondition(
    RuleCondition inner
) implements RuleCondition {

    public static final MapCodec<NotCondition> CODEC;

    @Override
    public boolean matches(TargetContext context) {
        return !inner.matches(context);
    }

    @Override
    public Type type() {
        return RuleConditionTypes.NOT;
    }

    static {
        CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                RuleCondition.CODEC
                    .fieldOf("condition").forGetter(NotCondition::inner)
            ).apply(instance, NotCondition::new)
        );
    }
}
