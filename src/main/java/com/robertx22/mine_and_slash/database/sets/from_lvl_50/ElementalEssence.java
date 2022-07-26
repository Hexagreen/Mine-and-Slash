package com.robertx22.mine_and_slash.database.sets.from_lvl_50;

import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.HashMap;

public class ElementalEssence extends Set {

    @Override
    public String locNameForLangFile() {
        return "Elemental Essence";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new ElementalResistFlat(Elements.Elemental));
                    put(3, new SpellDamageFlat());

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly(), LevelRequirement.fromHighLevel());
    }

    @Override
    public String GUID() {
        return "elemental_essence";
    }
}
