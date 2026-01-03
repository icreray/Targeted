package io.creray.targeted.client.crosshair.rule.condition;

import com.mojang.serialization.MapCodec;
import io.creray.targeted.client.target.TargetContext;

@FunctionalInterface
public interface RuleCondition {
    boolean matches(TargetContext context);
}
