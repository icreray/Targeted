package io.creray.targeted.client.crosshair.rule.condition;

import com.mojang.serialization.MapCodec;
import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.util.ModIdentifier;
import lombok.experimental.UtilityClass;
import net.minecraft.core.Registry;

@UtilityClass
public class RuleConditionTypes {

    public final RuleCondition.Type SIMPLE;

    static {
        SIMPLE = register("simple", ModRegistries.SIMPLE_RULE_CONDITION.byNameCodec().fieldOf("id"));
    }

    public void registerAll() {
        // Initialize static constructor
    }

    private RuleCondition.Type register(String path, MapCodec<? extends RuleCondition> codec) {
        return Registry.register(
            ModRegistries.RULE_CONDITION_TYPE,
            ModIdentifier.of(path),
            new RuleCondition.Type(codec)
        );
    }
}
