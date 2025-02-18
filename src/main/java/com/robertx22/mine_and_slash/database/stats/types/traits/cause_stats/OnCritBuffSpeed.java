package com.robertx22.mine_and_slash.database.stats.types.traits.cause_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.effects.cause_effects.GivePotionEffect;
import com.robertx22.mine_and_slash.database.stats.effects.cause_effects.OnCauseDoEffect;
import com.robertx22.mine_and_slash.database.stats.effects.causes.OnAttackCritCause;
import com.robertx22.mine_and_slash.database.stats.effects.causes.OnAttackDodgedCause;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import net.minecraft.potion.Effects;

import java.util.Arrays;
import java.util.List;

public class OnCritBuffSpeed extends Trait implements IStatEffects {

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public String locDescForLangFile() {
        return "On Crit, 25 Percent Chance to Buff Speed";
    }

    @Override
    public String locNameForLangFile() {
        return "Assassin";
    }

    @Override
    public String GUID() {
        return "assassin";
    }

    @Override
    public IStatEffect getEffect() {
        return new OnCauseDoEffect(
            new OnAttackCritCause(), 25, IStatEffect.EffectSides.Source, new GivePotionEffect(Effects.SPEED, 3),
            IStatEffect.EffectSides.Source
        );
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList();
    }
}


