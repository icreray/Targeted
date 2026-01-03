package io.creray.targeted.client.crosshair.rule.condition;

import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.util.BlockUtils;
import io.creray.targeted.util.ModIdentifier;
import lombok.experimental.UtilityClass;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.LivingEntity;

@UtilityClass
public class SimpleRuleConditions {

    public final RuleCondition IS_LIVING_ENTITY;
    public final RuleCondition IS_WAXED_BLOCK;

    static {
        IS_LIVING_ENTITY = ctx -> ctx.entity() instanceof LivingEntity;
        IS_WAXED_BLOCK = ctx -> BlockUtils.isWaxed(ctx.blockState());
    }

    public void registerAll() {
        register("is_living_entity", IS_LIVING_ENTITY);
        register("is_waxed_block", IS_WAXED_BLOCK);
    }

    private void register(String path, RuleCondition condition) {
        Registry.register(
            ModRegistries.SIMPLE_RULE_CONDITION,
            ModIdentifier.of(path),
            condition
        );
    }
}
