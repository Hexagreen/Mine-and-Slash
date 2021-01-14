package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponDamageMulti;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponSwingCost;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.NormalWeaponMechanic;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.WeaponMechanic;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.*;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.SpellDamagePercent;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemStaff;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Staff extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Staff();

    private Staff() {

    }

    @Override
    public String resourceID() {
        return "staff";
    }

    @Override
    public boolean isMageWeapon() {
        return true;
    }

    @Override
    public boolean isMeleeWeapon() {
        return true;
    }

    static StatReq req = new StatReq(LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM);

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.MAGE;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "staff";
    }

    @Override
    public WeaponDamageMulti weaponDamageMulti() {
        return new WeaponDamageMulti(1);
    }

    @Override
    public Item getDefaultItem() {
        return ItemStaff.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemStaff.Items;
    }

    @Override
    public WeaponSwingCost getSwingCosts() {
        return new WeaponSwingCost(8);
    }

    @Override
    public WeaponMechanic getWeaponMechanic() {
        return new NormalWeaponMechanic();
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Staff;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(new SpellDamageFlat(), new HealPowerFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Staff";
    }
}