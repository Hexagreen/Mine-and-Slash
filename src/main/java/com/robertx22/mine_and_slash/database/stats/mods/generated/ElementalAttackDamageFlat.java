package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class ElementalAttackDamageFlat extends ElementalStatMod {

    public ElementalAttackDamageFlat(Elements element) {
        super(element);

    }

    @Override
    public final Stat GetBaseStat() {
        return new ElementalAttackDamage(element);
    }

    @Override
    public MapWrapper<Elements, ElementalAttackDamage> getBaseStatMap() {
        return ElementalAttackDamage.MAP;
    }

    @Override
    public float Min() {
        return 3;
    }

    @Override
    public float Max() {
        return 7;
    }

    @Override
    public float minSecond() {
        return 6;
    }

    @Override
    public float maxSecond() {
        return 12;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalAttackDamageFlat(element);
    }
}
