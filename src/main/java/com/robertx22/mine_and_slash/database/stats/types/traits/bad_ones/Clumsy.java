package com.robertx22.mine_and_slash.database.stats.types.traits.bad_ones;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;

import java.util.Arrays;
import java.util.List;

public class Clumsy extends Trait {

    public static String GUID = "clumsy";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new PhysicalDamagePercent().size(StatMod.Size.ONE_LESS));

    }

    @Override
    public String locNameForLangFile() {
        return "Clumsy";
    }
}
