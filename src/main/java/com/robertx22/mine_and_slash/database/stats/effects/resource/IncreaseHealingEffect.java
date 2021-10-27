package com.robertx22.mine_and_slash.database.stats.effects.resource;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseHealEffect;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalInfusion;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealPower;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.HealEffect;

public class IncreaseHealingEffect extends BaseHealEffect {

    @Override
    public int GetPriority() {
        return Priority.Second.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public HealEffect activate(HealEffect effect, StatData data, Stat stat) {

        effect.number *= data.getMultiplier();

        return effect;
    }

    @Override
    public boolean canActivate(HealEffect effect, StatData data, Stat stat) {
        return true;
    }

}
