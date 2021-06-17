package com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.AllEleDmgEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class AllEleDmg extends Stat implements IStatEffects {

    public static String GUID = "all_ele_dmg";

    @Override
    public String locDescForLangFile() {
        return "Increases All Elemental DMG, both spells and attacks";
    }

    public AllEleDmg() {

    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Damage;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public IStatEffect getEffect() {
        return new AllEleDmgEffect();
    }

    @Override
    public String locNameForLangFile() {
        return "All Elemental Damage";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements getElement() {
        return Elements.Physical;
    }
}
