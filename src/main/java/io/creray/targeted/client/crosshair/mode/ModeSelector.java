package io.creray.targeted.client.crosshair.mode;

import io.creray.targeted.client.crosshair.rule.RuleSet;
import io.creray.targeted.client.resources.ModesLoader;
import io.creray.targeted.client.resources.RulesLoader;
import io.creray.targeted.client.target.Target;
import io.creray.targeted.client.target.TargetContext;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.resources.Identifier;

import java.util.Map;
import java.util.Objects;

public final class ModeSelector {

    private ModeMap modes;
    private RuleSet rules;

    {
        this.modes = ModeMap.EMPTY;
        this.rules = RuleSet.EMPTY;
    }

    public Mode selectFor(Target target) {
        var mode = switch (target) {
            case Target.Entity(var entity) ->
                rules.selectForEntity(TargetContext.of(entity));
            case Target.Block(var state, var position) ->
                rules.selectForBlock(TargetContext.of(state, position));
            default ->
                rules.selectForEmpty(TargetContext.empty());
        };
        return Objects.requireNonNullElse(mode, ModeMap.DEFAULT_MODE);
    }

    public void registerResourceReloaders(ResourceLoader loader) {
        loader.registerReloader(
            ModesLoader.ID,
            new ModesLoader(this::setLoadedModes)
        );
        loader.registerReloader(
            RulesLoader.ID,
            new RulesLoader(() -> this.modes, this::setLoadedRules)
        );
        loader.addReloaderOrdering(ModesLoader.ID, RulesLoader.ID);
    }

    private void setLoadedModes(Map<Identifier, Mode> loadedModes) {
        modes = new ModeMap(loadedModes);
    }

    private void setLoadedRules(RuleSet loadedRules) {
        rules = loadedRules;
    }
}
