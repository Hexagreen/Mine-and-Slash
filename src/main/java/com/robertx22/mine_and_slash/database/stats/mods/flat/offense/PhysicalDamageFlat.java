package com.robertx22.mine_and_slash.database.stats.mods.flat.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class PhysicalDamageFlat extends StatMod {

    public PhysicalDamageFlat() {
    }

    @Override
    public float Min() {
        return 3;
    }

    @Override
    public float Max() {
        return 5;
    }

    @Override
    public float minSecond() {
        return 4;
    }

    @Override
    public float maxSecond() {
        return 9;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return PhysicalDamage.getInstance();
    }

}
