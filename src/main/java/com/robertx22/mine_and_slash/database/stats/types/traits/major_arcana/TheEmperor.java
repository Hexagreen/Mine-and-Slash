package com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalInfusionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ElementalAttackDamagePercent;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Stamina;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Strength;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Vitality;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Wisdom;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class TheEmperor extends BaseMajorArcana {

    public static final String GUID = "the_emperor";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new CoreStatFlat(Vitality.INSTANCE), new CoreStatFlat(Strength.INSTANCE),
            new ElementalAttackDamagePercent(Elements.Nature).size(StatMod.Size.HALF_MORE)
        );
    }

    @Override
    public String locNameForLangFile() {
        return "The Emperor";
    }
}
