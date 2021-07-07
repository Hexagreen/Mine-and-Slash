package com.robertx22.mine_and_slash.database.gearitemslots.offhand;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseOffHand;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.BlockStrengthPercent;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.offhands.NormalShield;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Shield extends BaseOffHand implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Shield();

    private Shield() {

    }

    @Override
    public String resourceID() {
        return "shield";
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(new ArmorFlat(), new BlockStrengthPercent());
    }

    static StatReq req = new StatReq(
        LvlPointStat.VITALITY, StatReq.Size.TINY, LvlPointStat.STRENGTH, StatReq.Size.TINY);

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.WARRIOR;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "shield";
    }

    @Override
    public Item getDefaultItem() {
        return NormalShield.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return NormalShield.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Shield";
    }
}
