package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ArmorPercent;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class Stamina extends BaseCoreStat {

    public static final String GUID = "stamina";
    public static Stamina INSTANCE = new Stamina();

    private Stamina() {

    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.YELLOW;
    }

    @Override
    public String getIconPath() {
        return "core/sta";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Health, Energy, and Energy Regen";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(
                new HealthFlat().size(StatMod.Size.QUARTER),
                new EnergyFlat().size(StatMod.Size.LOW),
                new EnergyRegenFlat().size(StatMod.Size.LOW));
    }

    @Override
    public String locNameForLangFile() {
        return "Stamina";
    }
}
