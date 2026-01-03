package io.creray.targeted.client.crosshair.rule;

import io.creray.targeted.client.crosshair.mode.Mode;
import io.creray.targeted.client.target.TargetContext;
import io.creray.targeted.util.ModIdentifier;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.List;

@Unmodifiable
public final class RuleSet {

    public static final RuleSet EMPTY;
    public static final List<Identifier> TRIGGERS = List.of(
        ModIdentifier.of("target_entity"),
        ModIdentifier.of("target_block"),
        ModIdentifier.of("target_empty")
    );

    private final Rule[] entityRules;
    private final Rule[] blockRules;
    private final Rule[] emptyRules;

    public RuleSet(
        Rule[] entityRules,
        Rule[] blockRules,
        Rule[] emptyRules
    ) {
        this.entityRules = entityRules;
        this.blockRules = blockRules;
        this.emptyRules = emptyRules;

        Arrays.sort(entityRules, Rule::comparePriorities);
        Arrays.sort(blockRules, Rule::comparePriorities);
        Arrays.sort(emptyRules, Rule::comparePriorities);
    }

    public @Nullable Mode selectForEntity(TargetContext context) {
        return select(entityRules, context);
    }

    public @Nullable Mode selectForBlock(TargetContext context) {
        return select(blockRules, context);
    }

    public @Nullable Mode selectForEmpty(TargetContext context) {
        return select(emptyRules, context);
    }

    private static @Nullable Mode select(Rule[] rules, TargetContext context) {
        for (var rule : rules) {
            if (rule.matches(context))
                return rule.mode().with(context);
        }
        return null;
    }

    static {
        var emptyArray = new Rule[0];
        EMPTY = new RuleSet(emptyArray, emptyArray, emptyArray);
    }
}
