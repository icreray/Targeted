package io.creray.targeted.client.crosshair.rule;

import io.creray.targeted.client.crosshair.mode.Mode;
import io.creray.targeted.client.target.TargetContext;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;

@Unmodifiable
public final class RuleSet {

    public static final RuleSet EMPTY;

    private final Rule[][] rulesByTrigger;

    public RuleSet(
        Rule[][] rulesByTrigger
    ) {
        this.rulesByTrigger = rulesByTrigger;

        for (var rules : rulesByTrigger)
            Arrays.sort(rules, Rule::comparePriorities);
    }

    public @Nullable Mode selectForEntity(TargetContext context) {
        return select(ModeTriggers.TARGET_ENTITY, context);
    }

    public @Nullable Mode selectForBlock(TargetContext context) {
        return select(ModeTriggers.TARGET_BLOCK, context);
    }

    public @Nullable Mode selectForEmpty(TargetContext context) {
        return select(ModeTriggers.TARGET_EMPTY, context);
    }

    public @Nullable Mode select(ModeTrigger trigger, TargetContext context) {
        return select(rulesByTrigger[trigger.id()], context);
    }

    private static @Nullable Mode select(Rule[] rules, TargetContext context) {
        for (var rule : rules) {
            if (rule.matches(context))
                return rule.mode().with(context);
        }
        return null;
    }

    static {
        var emptyArray = new Rule[ModeTriggers.count()][0];
        EMPTY = new RuleSet(emptyArray);
    }
}
