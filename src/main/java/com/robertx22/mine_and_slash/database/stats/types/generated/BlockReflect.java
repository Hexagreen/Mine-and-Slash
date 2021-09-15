package com.robertx22.mine_and_slash.database.stats.types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.BlockReflectEffect;
import com.robertx22.mine_and_slash.database.stats.types.ElementalStat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

import java.util.ArrayList;
import java.util.List;

public class BlockReflect extends ElementalStat implements IStatEffects {

    public static MapWrapper<Elements, BlockReflect> MAP = new MapWrapper();

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Elements> elements = Elements.getAllIncludingPhysical();
        List<Stat> list = new ArrayList<>();
        elements.forEach(x -> list.add(newGeneratedInstance(x)));

        list.forEach(x -> MAP.put(x.getElement(), (BlockReflect) x));

        return list;

    }

    public BlockReflect(Elements element) {
        super(element);

    }

    @Override
    public Stat newGeneratedInstance(Elements element) {
        return new BlockReflect(element);
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locDescForLangFile() {
        return "Upon being hit, retaliate and damage the enemy (does not apply upon dodge).";
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "block_reflect";
    }

    @Override
    public String locNameForLangFile() {
        return element.dmgName + " Thorns";
    }

    @Override
    public String GUID() {
        return element.guidName + "_thorns_reflect";
    }

    @Override
    public IStatEffect getEffect() {
        return new BlockReflectEffect(element);
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Defenses;
    }
}
