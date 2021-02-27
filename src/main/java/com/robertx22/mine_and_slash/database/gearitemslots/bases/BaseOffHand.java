package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.BlockStrengthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.BlockStrengthPercent;
import net.minecraft.inventory.EquipmentSlotType;

import java.util.Arrays;
import java.util.List;

public abstract class BaseOffHand extends GearItemSlot {
    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return EquipmentSlotType.OFFHAND;
    }

    @Override
    public final int Weight() {
        return super.Weight() / 2;
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(new PosStats(new BlockStrengthFlat().size(StatMod.Size.TRIPLE)));
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(new ArmorFlat());
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.OffHand;
    }
}
