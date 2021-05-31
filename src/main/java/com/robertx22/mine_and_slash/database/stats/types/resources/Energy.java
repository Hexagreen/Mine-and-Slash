package com.robertx22.mine_and_slash.database.stats.types.resources;

import com.robertx22.mine_and_slash.database.stats.FillableStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.TextFormatting;

public class Energy extends FillableStat {

    public static String GUID = "energy";

    public static Energy getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.YELLOW;
    }

    @Override
    public String getIcon() {
        return "\u25CE";
    }

    @Override
    public String getIconPath() {
        return "resource/energy";
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Main;
    }

    @Override
    public String locDescForLangFile() {
        return "Energy is used to do basic attacks";
    }

    private Energy() {

    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return "Energy";
    }

    private static class SingletonHolder {
        private static final Energy INSTANCE = new Energy();
    }
}
