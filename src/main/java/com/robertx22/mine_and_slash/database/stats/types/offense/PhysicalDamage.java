package com.robertx22.mine_and_slash.database.stats.types.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class PhysicalDamage extends Stat {

    public static String GUID = "physical_damage";

    public static PhysicalDamage getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Damage;
    }

    @Override
    public String getIconPath() {
        return "phys_dmg";
    }

    @Override
    public String locDescForLangFile() {
        return "How much DMG your basic attacks do";
    }

    private PhysicalDamage() {
        this.BaseFlat = 1;
        this.minimumValue = 0;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements getElement() {
        return Elements.Physical;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.WEAPON;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return "Physical Damage";
    }

    private static class SingletonHolder {
        private static final PhysicalDamage INSTANCE = new PhysicalDamage();
    }
}
