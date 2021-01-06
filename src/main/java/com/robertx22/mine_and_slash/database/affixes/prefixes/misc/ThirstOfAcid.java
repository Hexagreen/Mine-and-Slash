package com.robertx22.mine_and_slash.database.affixes.prefixes.misc;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.SpellStealFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class ThirstOfAcid extends BaseThirstPrefix {

    @Override
    public String GUID() {
        return "thirst_of_acid";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new SpellStealFlat().size(StatMod.Size.HALF), new ElementalSpellDamageFlat(Elements.Nature));
    }

    @Override
    public String locNameForLangFile() {
        return "Thirst Of Acid";
    }
}
