package com.robertx22.mine_and_slash.database.currency;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class OrbOfChancesItem extends CurrencyItem implements ICurrencyItemEffect, IShapedRecipe {
    @Override
    public String GUID() {
        return "currency/reroll_secondary_stats_numbers";
    }

    private static final String name = Ref.MODID + ":currency/reroll_secondary_stats_numbers";

    public OrbOfChancesItem() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.secondaryStats.RerollNumbers(gear);

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, SimpleGearLocReq.HAS_SECONDARY_STATS);
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getRarityRank() {
        return Epic;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Everyone deserves a second chance.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Orb of Chances";
    }

    @Override
    public String locDescForLangFile() {
        return "Re-rolls secondary stat numbers";
    }

    @Override
    public int instabilityAddAmount() {
        return 10;
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.ORB_OF_CHANCES.get())
            .key('#', SimpleMatItem.GOLDEN_ORB)
            .key('t', ModItems.LEAF_OF_CHANGE.get())
            .key('v', Items.GOLD_INGOT)
            .key('o', ItemOre.ItemOres.get(IRarity.Epic))
            .patternLine("#t#")
            .patternLine("tvt")
            .patternLine("oto")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10));
    }

}