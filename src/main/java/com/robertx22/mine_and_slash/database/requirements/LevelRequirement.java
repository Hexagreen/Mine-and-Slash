package com.robertx22.mine_and_slash.database.requirements;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.requirements.bases.BaseRequirement;
import com.robertx22.mine_and_slash.database.requirements.bases.GearRequestedFor;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LevelRequirement extends BaseRequirement<LevelRequirement> {

    public int minLevel = 0;
    public int maxLevel = Integer.MAX_VALUE;

    private LevelRequirement(int minLevel) {
        this.minLevel = minLevel;
    }

    public LevelRequirement() {

    }

    @Override
    public boolean equals(Object other) {

        if (other instanceof LevelRequirement) {
            LevelRequirement l = (LevelRequirement) other;

            return l.minLevel == minLevel && l.maxLevel == maxLevel;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minLevel, maxLevel);
    }

    public int clampedMax() {
        return MathHelper.clamp(maxLevel, 0, ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get());
    }

    public static LevelRequirement none() {
        return new LevelRequirement(0, Integer.MAX_VALUE);
    }

    private LevelRequirement(int minLevel, int maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.add("min_level", new JsonPrimitive(minLevel));
        json.add("max_level", new JsonPrimitive(maxLevel));
        return json;
    }

    @Override
    public LevelRequirement fromJson(JsonObject json) {
        try {
            return new LevelRequirement(json.get("min_level")
                .getAsInt(), json.get("max_level")
                .getAsInt());
        } catch (Exception e) {
            return null;
        }
    }

    public static LevelRequirement lowLVLOnly() {
        return new LevelRequirement(0, 10);
    }

    public static LevelRequirement midLVLOnly() {
        return new LevelRequirement(10, 25);
    }

    public static LevelRequirement highLVLOnly() {
        return new LevelRequirement(25, 50);
    }

    public static LevelRequirement levelingProcess() {
        return new LevelRequirement(10, 50);
    }

    public static LevelRequirement endgameLVLOnly() {
        return new LevelRequirement(50, Integer.MAX_VALUE);
    }

    public static LevelRequirement fromLowLevel() {
        return new LevelRequirement(10);
    }

    public static LevelRequirement fromMidLevel() {
        return new LevelRequirement(25);
    }

    public static LevelRequirement fromHighLevel() {
        return new LevelRequirement(50);
    }

    @Override
    public boolean meetsRequierment(GearRequestedFor requested) {

        int maxPlayerlvl = ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get();

        minLevel = MathHelper.clamp(minLevel, 0,
            maxPlayerlvl
        );  // make sure min lvl is not higher than the maximum posible level in case it was decreased by config?
        maxLevel = MathHelper.clamp(maxLevel, 0,
            maxPlayerlvl
        );  // make sure min lvl is not higher than the maximum posible level in case it was decreased by config?

        int level = requested.gearData.level;

        if (level < minLevel || level > maxLevel) {
            return false;
        }

        return true;

    }

    @Override
    public String getJsonID() {
        return "level_req";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        return Arrays.asList(new SText(TextFormatting.YELLOW + "Level: " + minLevel + " - " + MathHelper.clamp(maxLevel, 0, ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get())));
    }
}