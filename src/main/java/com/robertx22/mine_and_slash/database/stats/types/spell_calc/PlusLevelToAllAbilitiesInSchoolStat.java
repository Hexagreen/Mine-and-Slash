package com.robertx22.mine_and_slash.database.stats.types.spell_calc;

import com.robertx22.mine_and_slash.database.stats.IAfterStatCalc;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class PlusLevelToAllAbilitiesInSchoolStat extends Stat implements IAfterStatCalc, IGenerated<PlusLevelToAllAbilitiesInSchoolStat> {

    private Masteries school;

    public PlusLevelToAllAbilitiesInSchoolStat(Masteries school) {
        this.school = school;
        this.isInt = true;
    }

    public Masteries getSchool() {
        return school;
    }

    @Override
    public boolean IsPercent() {
        return false;
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
        return "Increase all allocated spell levels of that school of magic.";
    }

    @Override
    public String locNameForLangFile() {
        return "To " + school.getFullNameTranslated() + " Spell Levels";
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "plus_all_skill_levels";
    }

    @Override
    public String GUID() {
        return "plus_" + school.id + "_skill_lvls";
    }

    @Override
    public List<PlusLevelToAllAbilitiesInSchoolStat> generateAllPossibleStatVariations() {
        List<PlusLevelToAllAbilitiesInSchoolStat> list = new ArrayList<>();

        for (Masteries value : Masteries.values()) {
            list.add(new PlusLevelToAllAbilitiesInSchoolStat(value));
        }

        return list;
    }

    @Override
    public void doAfterStatCalc(StatData data, EntityCap.UnitData unit, PlayerSpellCap.ISpellsCap spells) {
        int lvls = (int) data
            .getAverageValue();
        if (lvls > 0) {
            spells.getAbilitiesData()
                .addBonusAbilityLevelsTo(getSchool(), lvls);
        }

    }

    @Override
    public boolean IsShownOnStatGui() {
        return false;
    }
}
