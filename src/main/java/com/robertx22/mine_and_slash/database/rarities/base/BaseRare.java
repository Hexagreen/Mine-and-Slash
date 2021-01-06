package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseRare implements Rarity {
    @Override
    public int colorInt() {
        return 5592575;
    }

    @Override
    public String GUID() {

        return "rare";
    }

    @Override
    public int Rank() {

        return IRarity.Rare;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
        return new MinMax(30, 50);
    }

    @Override
    public TextFormatting textFormatting() {
        return TextFormatting.BLUE;
    }

    @Override
    public String locNameForLangFile() {
        return "Rare";
    }
}
