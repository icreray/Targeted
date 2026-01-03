package io.creray.targeted.client.resources;

import io.creray.targeted.client.ModRegistries;
import io.creray.targeted.client.crosshair.mode.ModeMap;
import io.creray.targeted.client.crosshair.rule.ModeTriggers;
import io.creray.targeted.client.crosshair.rule.Rule;
import io.creray.targeted.client.crosshair.rule.RuleSet;
import io.creray.targeted.util.ModIdentifier;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

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
        Map<Identifier, RuleDefinition> ruleDefinitions,
        ResourceManager resourceManager,
        ProfilerFiller profilerFiller
    ) {
        var modes = modesSupplier.get();
        var compiler = new RuleCompiler(modes);
        var rulesByTrigger = new Rule[ModeTriggers.count()][];

        for (var trigger : ModRegistries.MODE_TRIGGER) {
            rulesByTrigger[trigger.id()] = ruleDefinitions.values()
                .stream()
                .filter(rule -> rule.trigger().equals(trigger))
                .map(compiler::compile)
                .toArray(Rule[]::new);
        }

        ruleSetter.accept(new RuleSet(rulesByTrigger));
    }
}
