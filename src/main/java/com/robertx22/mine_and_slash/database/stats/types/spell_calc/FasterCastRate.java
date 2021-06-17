package com.robertx22.mine_and_slash.database.stats.types.spell_calc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.spell_calc.ReduceCastTimeEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class FasterCastRate extends Stat implements IStatEffects {

    private FasterCastRate() {
        this.maximumValue = 75;
    }

    public static FasterCastRate getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public String locDescForLangFile() {
        return "Reduces amount of time needed to cast spells.";
    }

    @Override
    public String locNameForLangFile() {
        return "Faster Cast Rate";
    }

    @Override
    public String GUID() {
        return "faster_cast_rate";
    }

    @Override
    public IStatEffect getEffect() {
        return new ReduceCastTimeEffect();
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.SpellDamage;
    }

    private static class SingletonHolder {
        private static final FasterCastRate INSTANCE = new FasterCastRate();
    }
}

