package com.robertx22.mine_and_slash.items.gearitems.armor.cloth;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.gearitems.bases.BaseArmorItem;
import com.robertx22.mine_and_slash.items.gearitems.bases.BaseDyableArmorItem;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;

import java.util.HashMap;

public class ClothChestItem extends BaseArmorItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ClothChestItem(int rarity) {
        super(Type.CLOTH, rarity, EquipmentSlotType.CHEST);

    }

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Gears.get(rarity);
        return rar.textFormatting() + "Cloth Shirt";
    }
}


