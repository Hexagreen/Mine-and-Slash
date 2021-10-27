package com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.ArmorPenetrationFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalInfusionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ElementalAttackDamagePercent;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class Justice extends BaseMajorArcana {

    public static final String GUID = "justice";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new WeaponDamageFlat(WeaponTypes.Sword), new ArmorPenetrationFlat(),
            new ElementalAttackDamagePercent(Elements.Thunder).size(StatMod.Size.HALF_MORE)
        );
    }

    @Override
    public String locNameForLangFile() {
        return "Justice";
    }
}
