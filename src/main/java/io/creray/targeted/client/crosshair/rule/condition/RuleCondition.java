package io.creray.targeted.client.crosshair.rule.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.client.target.TargetContext;

@FunctionalInterface
public interface RuleCondition {

    Codec<RuleCondition> TYPED_CODEC = ModRegistries.RULE_CONDITION_TYPE
        .byNameCodec()
        .dispatch("type", RuleCondition::type, RuleCondition.Type::codec);
    Codec<RuleCondition> SIMPLE_CODEC = ModRegistries.SIMPLE_RULE_CONDITION.byNameCodec();
    Codec<RuleCondition> CODEC = Codec.withAlternative(SIMPLE_CODEC, TYPED_CODEC);

    boolean matches(TargetContext context);
    default RuleCondition.Type type() { return RuleConditionTypes.SIMPLE; }

    record Type(MapCodec<? extends RuleCondition> codec) {}
}
