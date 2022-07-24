package com.robertx22.mine_and_slash.database.stats.types.traits.ele_lords;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.CooldownReductionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.IncreasedDurationFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysToFireConvFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.AllElementalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalInfusionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ElementalAttackDamagePercent;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class LordOfThunderstormsTrait extends Trait implements IAffectsOtherStats {

    @Override
    public List<StatMod> getStats() {

        return Arrays.asList(new CooldownReductionFlat(), new IncreasedDurationFlat());

    }

    @Override
    public String GUID() {
        return "lord_of_thunderstorms";
    }

    @Override
    public String locNameForLangFile() {
        return "Lord of Thunderstorms";
    }
}
