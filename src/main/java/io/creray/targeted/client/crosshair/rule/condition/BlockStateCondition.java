package io.creray.targeted.client.crosshair.rule.condition;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.creray.targeted.client.target.TargetContext;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.Optional;

/*
 This class is copy of net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition :)
 */
public record BlockStateCondition(
    Holder<Block> block,
    Optional<StatePropertiesPredicate> properties
) implements RuleCondition {

    public static final MapCodec<BlockStateCondition> CODEC = RecordCodecBuilder.<BlockStateCondition>mapCodec(
        instance -> instance.group(
            BuiltInRegistries.BLOCK.holderByNameCodec().fieldOf("block").forGetter(BlockStateCondition::block),
            StatePropertiesPredicate.CODEC.optionalFieldOf("properties").forGetter(BlockStateCondition::properties)
        ).apply(instance, BlockStateCondition::new)
    ).validate(BlockStateCondition::validate);

    @VisibleForTesting
    @SuppressWarnings({"unchecked", "rawtypes"})
    private DataResult<BlockStateCondition> validate() {
        var blockStateDef = block.value().getStateDefinition();
        return (DataResult) properties
            .flatMap(predicate -> predicate.checkState(blockStateDef))
            .map(property -> DataResult.error(() -> "Block " + block + " has no property " + property))
            .orElse(DataResult.success(this));
    }

    @Override
    public boolean matches(TargetContext context) {
        var targetState = context.blockState();
        assert targetState != null;
        return targetState.is(block) && (properties.isEmpty() || properties.get().matches(targetState));
    }

    @Override
    public Type type() {
        return RuleConditionTypes.BLOCK_STATE;
    }
}
