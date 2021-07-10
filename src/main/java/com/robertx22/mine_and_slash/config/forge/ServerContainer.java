package com.robertx22.mine_and_slash.config.forge;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import net.minecraft.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.minecraftforge.common.ForgeConfigSpec.*;

public class ServerContainer {

    public BooleanValue USE_COMPATIBILITY_ITEMS;
    public BooleanValue DISABLE_VANILLA_HP_REGEN;
    public BooleanValue GENERATE_ORES;
    public IntValue DELETE_DUNGEON_DIMENSION_FOLDER_AFTER_X_MAPS_SACRIFICED;
    public BooleanValue ONLY_REPAIR_IN_STATION;
    public BooleanValue LOG_REGISTRY_ENTRIES;

    public BooleanValue ENABLE_CURRENCY_ITEMS_BREAKING_MODIFIED_ITEMS;
    public BooleanValue ENABLE_CURRENCY_ITEMS_INSTABILITY_SYSTEM;

    public IntValue MAXIMUM_WORN_RUNED_ITEMS;
    public IntValue MAXIMUM_WORN_UNIQUE_ITEMS;
    public IntValue MAPS_DROP_AFTER_LEVEL;
    public IntValue CURRENCY_DROP_AFTER_LEVEL;
    public IntValue RUNES_AND_RUNED_GEAR_DROP_AFTER_LEVEL;
    public IntValue MAXIMUM_PLAYER_LEVEL;
    public IntValue MAXIMUM_ITEM_INSTABILITY;
    public IntValue FUEL_NEEDED_PER_MAP_ACTIVATION;

    public IntValue TALENT_POINTS_AT_MAX_LEVEL;
    public IntValue SPELL_POINTS_AT_MAX_LEVEL;

    public IntValue STARTING_SPELL_POINTS;

    public DoubleValue STAT_POINTS_PER_LEVEL;
    public DoubleValue MOB_BOSS_CHANCE_IN_MAPS;
    public DoubleValue NON_MOD_DAMAGE_MULTI;
    public DoubleValue XP_LOSS_ON_DEATH;
    public DoubleValue MOB_ENVIRONMENT_DAMAGE_MULTI;
    public DoubleValue NON_MOD_HEAL_MULTI;
    public DoubleValue EXPERIENCE_MULTIPLIER;
    public DoubleValue UNARMED_ENERGY_COST;
    public DoubleValue PLAYER_HEART_TO_HEALTH_CONVERSION;
    public DoubleValue STAT_REQUIREMENTS_MULTI;
    public DoubleValue MAP_EVENT_CHANCE_PER_MINUTE;
    public DoubleValue REPAIR_FUEL_NEEDED_MULTI;
    public DoubleValue MOB_STRENGTH_PER_LEVEL_MULTI;
    public DoubleValue REGEN_HUNGER_COST;

    public ConfigValue<List<? extends String>> IGNORED_ENTITIES;

    ServerContainer(Builder builder) {
        builder.push("GENERAL");

        List<EntityType> list = new ArrayList<>();
        list.add(EntityType.BAT);
        list.add(EntityType.TROPICAL_FISH);
        list.add(EntityType.PUFFERFISH);
        list.add(EntityType.SALMON);
        list.add(EntityType.COD);
        list.add(EntityType.SQUID);
        list.add(EntityType.RABBIT);

        List<String> idlist = list.stream()
            .map(x -> x.getRegistryName()
                .toString())
            .collect(Collectors.toList());

        IGNORED_ENTITIES = builder.comment(".")
            .translation("mmorpg.word.")
            .defineList("IGNORED_ENTITIES", idlist, x -> true);

        REGEN_HUNGER_COST = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("REGEN_HUNGER_COST", 3D, 0, 1000D);

        MOB_STRENGTH_PER_LEVEL_MULTI = builder.comment("This makes mobs increasingly weaker, by default at lvl 100 mobs will be 3x stronger, formula: Math.pow(lvl, (lvl / VALUE)) - 0.5")
            .translation("mmorpg.word.")
            .defineInRange("MOB_STRENGTH_PER_LEVEL_MULTI", 360F, 0, 1000D);

        FUEL_NEEDED_PER_MAP_ACTIVATION = builder.comment("")
            .translation("mmorpg.word.")
            .defineInRange("FUEL_NEEDED_PER_MAP_ACTIVATION", 2500, 0, 100000);

        REPAIR_FUEL_NEEDED_MULTI = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("REPAIR_FUEL_NEEDED_MULTI", 1D, 0, 100000D);

        MAP_EVENT_CHANCE_PER_MINUTE = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("MAP_EVENT_CHANCE_PER_MINUTE", 10D, 0, 100);

        MOB_BOSS_CHANCE_IN_MAPS = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("MOB_BOSS_CHANCE_IN_MAPS", 0.2D, 0, 100);

        STAT_POINTS_PER_LEVEL = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("STAT_POINTS_PER_LEVEL", 1.5D, 0, 100);

        TALENT_POINTS_AT_MAX_LEVEL = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("TALENT_POINTS_AT_MAX_LEVEL", 120, 0, 10000);

        SPELL_POINTS_AT_MAX_LEVEL = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("SPELL_POINTS_AT_MAX_LEVEL", (Masteries.MAXIMUM_POINTS * 2) + 80, 0, 1000);

        STARTING_SPELL_POINTS = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("STARTING_SPELL_POINTS", 3, 0, Integer.MAX_VALUE);

        PLAYER_HEART_TO_HEALTH_CONVERSION = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("PLAYER_HEART_TO_HEALTH_CONVERSION", 1D, 0D, 100D);

        STAT_REQUIREMENTS_MULTI = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("STAT_REQUIREMENTS_MULTI", 1D, 0D, 100D);

        XP_LOSS_ON_DEATH = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("XP_LOSS_ON_DEATH", 0.25D, 0D, 1D);

        ENABLE_CURRENCY_ITEMS_BREAKING_MODIFIED_ITEMS = builder.comment(".")
            .define("ENABLE_CURRENCY_ITEMS_BREAKING_MODIFIED_ITEMS", true);
        LOG_REGISTRY_ENTRIES = builder.comment(".")
            .define("LOG_REGISTRY_ENTRIES", false);

        ENABLE_CURRENCY_ITEMS_INSTABILITY_SYSTEM = builder.comment(".")
            .define("ENABLE_CURRENCY_ITEMS_INSTABILITY_SYSTEM", true);

        DELETE_DUNGEON_DIMENSION_FOLDER_AFTER_X_MAPS_SACRIFICED = builder.comment(".")
            .translation("mmorpg.word")
            .defineInRange("DELETE_DUNGEON_DIMENSION_FOLDER_AFTER_X_MAPS_SACRIFICED", 5000, 0, 500000);

        ONLY_REPAIR_IN_STATION = builder.comment(".")
            .define("ONLY_REPAIR_IN_STATION", true);

        DISABLE_VANILLA_HP_REGEN = builder.comment(".")
            .translation("mmorpg.word.entities")
            .define("DISABLE_VANILLA_HP_REGEN", true);

        USE_COMPATIBILITY_ITEMS = builder.comment(".")
            .translation("mmorpg.word.")
            .define("USE_COMPATIBILITY_ITEMS", true);

        GENERATE_ORES = builder.comment(".")
            .translation("mmorpg.word.")
            .define("GENERATE_ORES", true);

        MAXIMUM_WORN_RUNED_ITEMS = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("MAXIMUM_WORN_RUNED_ITEMS", 3, 0, Integer.MAX_VALUE);

        MAXIMUM_WORN_UNIQUE_ITEMS = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("MAXIMUM_WORN_UNIQUE_ITEMS", 4, 0, Integer.MAX_VALUE);

        MAPS_DROP_AFTER_LEVEL = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("MAPS_DROP_AFTER_LEVEL", 10, 0, Integer.MAX_VALUE);

        MAXIMUM_ITEM_INSTABILITY = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("MAXIMUM_ITEM_INSTABILITY", 500, 0, Integer.MAX_VALUE);

        CURRENCY_DROP_AFTER_LEVEL = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("CURRENCY_DROP_AFTER_LEVEL", 10, 0, Integer.MAX_VALUE);

        RUNES_AND_RUNED_GEAR_DROP_AFTER_LEVEL = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("RUNES_AND_RUNED_GEAR_DROP_AFTER_LEVEL", 8, 0, Integer.MAX_VALUE);

        MAXIMUM_PLAYER_LEVEL = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("MAXIMUM_PLAYER_LEVEL", 100, 0, Integer.MAX_VALUE);

        NON_MOD_DAMAGE_MULTI = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("NON_MOD_DAMAGE_MULTI", 0D, 0D, Integer.MAX_VALUE);

        MOB_ENVIRONMENT_DAMAGE_MULTI = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("MOB_ENVIRONMENT_DAMAGE_MULTI", 0.2D, 0D, Integer.MAX_VALUE);

        NON_MOD_HEAL_MULTI = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("NON_MOD_HEAL_MULTI", 0.1D, 0D, Integer.MAX_VALUE);

        EXPERIENCE_MULTIPLIER = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("EXPERIENCE_MULTIPLIER", 1D, 0D, Integer.MAX_VALUE);

        UNARMED_ENERGY_COST = builder.comment(".")
            .translation("mmorpg.word.")
            .defineInRange("UNARMED_ENERGY_COST", 1.5D, 0D, Integer.MAX_VALUE);

        builder.pop();
    }

}
