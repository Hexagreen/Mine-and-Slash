package com.robertx22.mine_and_slash.blocks.slots;

import com.robertx22.mine_and_slash.items.ores.ItemOre;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.HashMap;

public class FuelSlot extends Slot {
    public FuelSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return FUEL_VALUES.get(stack.getItem()) != null;
    }

    public static HashMap<Item, Integer> FUEL_VALUES = new HashMap<Item, Integer>() {
        {
            {

                put(Items.DIAMOND, 1000);
                put(Items.GOLD_INGOT, 100);
                put(Items.IRON_INGOT, 50);
                put(Items.EMERALD, 500);
                put(Items.REDSTONE, 10);

                int ore = 3;

                for (int i = 0; i < 5; i++) {
                    put(ItemOre.ItemOres.get(i), ore);
                    ore *= 9;

                }

            }
        }
    };

}