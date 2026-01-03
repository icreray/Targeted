package io.creray.targeted.client.crosshair.rule;

import io.creray.targeted.client.crosshair.mode.Mode;
import io.creray.targeted.client.crosshair.rule.condition.RuleCondition;
import io.creray.targeted.client.target.TargetContext;

public record Rule(
    int priority,
    RuleCondition[] conditions,
    Mode mode
) {
    public boolean matches(TargetContext context) {
        for (var condition : conditions) {
            if (!condition.matches(context))
                return false;
        }
        return true;
    }

    public static int comparePriorities(Rule a, Rule b) {
        return Integer.compare(b.priority, a.priority);
    }
}
