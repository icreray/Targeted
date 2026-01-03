package io.creray.targeted.client.resources;

import io.creray.targeted.Targeted;
import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.client.crosshair.mode.ModeMap;
import io.creray.targeted.client.crosshair.rule.ModeTrigger;
import io.creray.targeted.client.crosshair.rule.ModeTriggers;
import io.creray.targeted.client.crosshair.rule.Rule;
import io.creray.targeted.client.crosshair.rule.RuleSet;
import io.creray.targeted.util.ModIdentifier;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class RulesLoader
    extends SimpleJsonResourceReloadListener<RuleDefinition>
{
    public static final Identifier ID = ModIdentifier.of("crosshair_rule");

    private final Supplier<ModeMap> modesSupplier;
    private final Consumer<RuleSet> ruleSetter;

    public RulesLoader(
        Supplier<ModeMap> modesSupplier,
        Consumer<RuleSet> ruleSetter
    ) {
        super(RuleDefinition.CODEC, FileToIdConverter.json("crosshair/rule"));
        this.modesSupplier = modesSupplier;
        this.ruleSetter = ruleSetter;
    }

    @Override
    protected void apply(
        @NotNull Map<Identifier, RuleDefinition> ruleDefinitions,
        @NotNull ResourceManager resourceManager,
        @NotNull ProfilerFiller profilerFiller
    ) {
        var definedModes = modesSupplier.get();
        var compiler = new RuleCompiler(definedModes);
        var rulesByTrigger = new Rule[ModeTriggers.count()][];

        for (var trigger : ModRegistries.MODE_TRIGGER) {
            rulesByTrigger[trigger.id()] = compileForTrigger(ruleDefinitions, compiler, trigger);
        }

        ruleSetter.accept(new RuleSet(rulesByTrigger));
    }

    private static Rule[] compileForTrigger(
        Map<Identifier, RuleDefinition> ruleDefinitions,
        RuleCompiler compiler,
        ModeTrigger trigger
    ) {
        var rules = new ArrayList<Rule>();
        for (var entry : ruleDefinitions.entrySet()) {
            var identifier = entry.getKey();
            var ruleDef = entry.getValue();
            if (!ruleDef.trigger().equals(trigger)) continue;
            try {
                var rule = compiler.compile(ruleDef);
                rules.add(rule);
            }
            catch (IllegalStateException e) {
                Targeted.LOGGER.error("Failed to compile rule '{}': {}", identifier, e.getMessage());
            }
        }
        return rules.toArray(Rule[]::new);
    }
}
