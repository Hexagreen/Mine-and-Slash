package com.robertx22.mine_and_slash.database.stats.types.spell_calc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.spell_calc.ReduceManaCostEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class ReducedManaCost extends Stat implements IStatEffects {

    private ReducedManaCost() {
        this.maximumValue = 75;
    }

    public static String GUID = "mana_cost_red";

    public static ReducedManaCost getInstance() {
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
    public String getIconPath() {
        return "resource/reduced_mana_cost";
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public String locDescForLangFile() {
        return "Reduces Mana Cost of all spells.";
    }

    @Override
    public String locNameForLangFile() {
        return "Mana Cost Reduction";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public IStatEffect getEffect() {
        return new ReduceManaCostEffect();
    }

    private static class SingletonHolder {
        private static final ReducedManaCost INSTANCE = new ReducedManaCost();
    }
}
