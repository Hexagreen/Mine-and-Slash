package com.robertx22.mine_and_slash.database.stats.mods.flat.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.PlusLevelToAllAbilitiesInSchoolStat;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.PlusLevelToAllAbilitiesStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class PlusAllSkillLevelsFlat extends StatMod implements IGenerated<StatMod> {

    @Override
    public Stat GetBaseStat() {
        return new PlusLevelToAllAbilitiesStat();
    }

    @Override
    public float Min() {
        return 1;
    }

    @Override
    public float Max() {
        return 1;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        list.add(new PlusAllSkillLevelsFlat());
        return list;
    }
}
