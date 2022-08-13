package com.robertx22.mine_and_slash.uncommon.localization;

import java.util.Iterator;
import java.util.List;

public class SpellType {
    public static String getSpellTypeStr(List<Spells> spellTypes) {
        String typeStr = "";
        Iterator<Spells> itr = spellTypes.iterator();

        typeStr = typeStr.concat(itr.next().locName().getString());

        while(itr.hasNext()) {
            typeStr = typeStr.concat(", ").concat(itr.next().locName().getString());
        }
        return typeStr;
    }
}
