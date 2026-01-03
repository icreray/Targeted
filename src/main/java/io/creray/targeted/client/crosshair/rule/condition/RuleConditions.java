package io.creray.targeted.client.crosshair.rule.condition;

import io.creray.targeted.util.BlockUtils;
import io.creray.targeted.util.ModIdentifier;
import lombok.experimental.UtilityClass;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@UtilityClass
public class RuleConditions {

    public final RuleCondition IS_LIVING_ENTITY;
    public final RuleCondition IS_WAXED_BLOCK;

    static {
        IS_LIVING_ENTITY = ctx -> ctx.entity() instanceof LivingEntity;
        IS_WAXED_BLOCK = ctx -> BlockUtils.isWaxed(ctx.blockState());
    }

    // TODO: Make simple (and complex conditions (dynamic))
    // TODO: Use registries?
    private final Map<Identifier, RuleCondition> REGISTRY = Map.of(
        ModIdentifier.of("is_living_entity"), IS_LIVING_ENTITY,
        ModIdentifier.of("is_waxed_block"), IS_WAXED_BLOCK
    );

    public @Nullable RuleCondition get(Identifier id) {
        return REGISTRY.get(id);
    }
}
