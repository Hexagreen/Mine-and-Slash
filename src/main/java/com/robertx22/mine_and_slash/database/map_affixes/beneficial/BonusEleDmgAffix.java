package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CompletePhysDispersionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDispersion;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IElementalGenerated;

import java.util.Arrays;
import java.util.List;

public class BonusEleDmgAffix extends BaseElementalMapAffix {

    public BonusEleDmgAffix(Elements element) {
        super(element);
    }

    @Override
    public StatMod.Size getSize() {
        return StatMod.Size.TRIPLE;
    }

    @Override
    public IElementalGenerated<StatMod> getGenStat() {
        return new ElementalAttackDamageFlat(Elements.Nature);
    }

    @Override
    public String GUID() {
        return "bonus_" + element.guidName + "_damage_affix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(getGenStat().newGeneratedInstance(element).size(getSize()), percent),
                StatModData.Load(new CompletePhysDispersionFlat(), 100));
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }

    @Override
    public BaseMapAffix newGeneratedInstance(Elements element) {
        return new BonusEleDmgAffix(element);
    }

    @Override
    public float lootMulti() {
        return 1.4F;
    }
}
