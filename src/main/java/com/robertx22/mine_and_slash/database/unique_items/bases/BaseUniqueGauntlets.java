package com.robertx22.mine_and_slash.database.unique_items.bases;

import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemGauntlets;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemHammer;

public final class BaseUniqueGauntlets extends ItemGauntlets {

    public BaseUniqueGauntlets() {
        super(UniqueGear.getInstance()
            .Rank());
    }

    @Override
    public boolean shouldRegisterLangName() {
        return false;
    }

}
