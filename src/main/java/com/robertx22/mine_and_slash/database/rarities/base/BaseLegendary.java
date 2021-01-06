package com.robertx22.mine_and_slash.database.rarities.base;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseLegendary implements Rarity {
    @Override
    public int colorInt() {
        return 16755200;
    }

    @Override
    public String GUID() {

        return "legendary";
    }

    @Override
    public int Rank() {

        return IRarity.Legendary;
    }

    @Override
    public TextFormatting textFormatting() {
        return TextFormatting.GOLD;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
        return new MinMax(50, 70);
    }

    @Override
    public String locNameForLangFile() {
        return "Legendary";
    }
}
