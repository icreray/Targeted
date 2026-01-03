package io.creray.targeted.client.resources;

import com.google.gson.JsonParseException;
import io.creray.targeted.client.crosshair.mode.ModeMap;
import io.creray.targeted.client.crosshair.rule.Rule;
import io.creray.targeted.client.crosshair.rule.condition.RuleCondition;

public record RuleCompiler(
    ModeMap modes
) {
    public Rule compile(RuleDefinition def) {
        var mode = modes.get(def.mode());
        if (mode == null)
            throw new JsonParseException("Invalid mode: " + def.mode()); // FIXME: proper error handling
        var conditions = def.conditions()
            .toArray(RuleCondition[]::new);
        return new Rule(
            def.priority(),
            conditions,
            mode
        );
    }
}
