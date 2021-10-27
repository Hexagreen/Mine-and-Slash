package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.MapBlueprint;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.ItemStack;

public class MapLootGen extends BaseLootGen<MapBlueprint> {

    public MapLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float baseDropChance() {
        float chance = ModConfig.INSTANCE.DropRates.MAP_DROPRATE.get()
            .floatValue();

        if (info.isMapWorld) {
            chance *= 1.2F;
        }

        return chance;

    }

    @Override
    public LootType lootType() {
        return LootType.Map;
    }

    @Override
    public boolean condition() {
        return info.level >= ModConfig.INSTANCE.Server.MAPS_DROP_AFTER_LEVEL.get();
    }

    @Override
    public boolean hasLevelDistancePunishment() {
        return false;
    }

    @Override
    public ItemStack generateOne() {
        MapBlueprint blueprint = new MapBlueprint(info.level, info.tier);

        return blueprint.createStack();
    }

}
