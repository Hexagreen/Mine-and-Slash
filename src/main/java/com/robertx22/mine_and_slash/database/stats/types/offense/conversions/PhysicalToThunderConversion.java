package com.robertx22.mine_and_slash.database.stats.types.offense.conversions;

import com.robertx22.mine_and_slash.database.stats.ConversionMethod;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatConversion;

import java.util.Arrays;
import java.util.List;

public class PhysicalToThunderConversion extends Stat implements IStatConversion {

    public static String GUID = "phys_to_thunder_conversion";

    @Override
    public List<ConversionMethod> conversion() {
        return Arrays.asList(new ConversionMethod(PhysicalDamage.getInstance(), new ElementalAttackDamage(Elements.Thunder)));

    }

    @Override
    public String getIcon() {
        return "\u2764";
    }

    @Override
    public String getIconPath() {
        return "ele_atk_dmg/phys_to_thunder";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public String locNameForLangFile() {
        return "Phys DMG Gained as Lightning Wep DMG";
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public String locDescForLangFile() {
        return "Adds to lightning weapon damage based on your physical damage.";
    }
}
