package io.creray.targeted.client.resources;

import io.creray.targeted.client.crosshair.mode.ModeMap;
import io.creray.targeted.client.crosshair.rule.Rule;
import io.creray.targeted.client.crosshair.rule.condition.RuleCondition;

public record RuleCompiler(
    ModeMap definedModes
) {
    public Rule compile(RuleDefinition def) throws IllegalStateException {
        var mode = definedModes.get(def.mode());
        if (mode == null)
            throw new IllegalStateException("Invalid mode argument '" + def.mode() + "'. It must reference a defined mode.");
        return new Rule(
            def.priority(),
            def.conditions().toArray(RuleCondition[]::new),
            mode
        );
    }
}
