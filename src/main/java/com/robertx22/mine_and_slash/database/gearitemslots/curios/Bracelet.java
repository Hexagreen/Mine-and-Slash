package com.robertx22.mine_and_slash.database.gearitemslots.curios;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseCurio;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.BonusExpFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.*;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.LootTypeBonusFlat;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemBracelet;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Bracelet extends BaseCurio {
    public static GearItemSlot INSTANCE = new Bracelet();

    private Bracelet() {

    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.NONE;
    }

    @Override
    public StatReq getRequirements() {
        return noReq;
    }

    @Override
    public String resourceID() {
        return "bracelet";
    }

    @Override
    public String GUID() {
        return "bracelet";
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(
                new PosStats(new HealthRegenFlat().size(StatMod.Size.HALF_MORE)),
                new PosStats(new ManaRegenFlat().size(StatMod.Size.TRIPLE)),
                new PosStats(new EnergyRegenFlat().size(StatMod.Size.TRIPLE)),
                new PosStats(new MagicShieldRegenFlat().size(StatMod.Size.HALF_MORE))
        );
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(
                new ElementalResistFlat(Elements.Fire).size(StatMod.Size.HALF),
                new ElementalResistFlat(Elements.Water).size(StatMod.Size.HALF),
                new ElementalResistFlat(Elements.Nature).size(StatMod.Size.HALF),
                new ElementalResistFlat(Elements.Thunder).size(StatMod.Size.HALF)
        );
    }

    @Override
    public Item getDefaultItem() {
        return ItemBracelet.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemBracelet.Items;
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Jewerly;
    }

    @Override
    public String locNameForLangFile() {
        return "Bracelet";
    }
}