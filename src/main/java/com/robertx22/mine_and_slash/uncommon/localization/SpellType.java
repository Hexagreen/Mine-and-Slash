package com.robertx22.mine_and_slash.uncommon.localization;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellPredicate;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellPredicates;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.Iterator;
import java.util.List;

public class SpellType {
    public static String getSpellTypeStr(List<Spells> spellTypes) {
        String typeStr = "";
        Iterator<Spells> itr = spellTypes.iterator();

        typeStr = typeStr.concat(itr.next().getLocNameStr());

        while(itr.hasNext()) {
            typeStr = typeStr.concat(", ").concat(itr.next().getLocNameStr());
        }
        return typeStr;
    }

    public static ITextComponent getSpellPreText(SpellPredicate spellPredicate) {
        if(spellPredicate.equals(SpellPredicates.REQUIRE_SHOOTABLE))
            return new SText(TextFormatting.RED + "" + TextFormatting.ITALIC + Spells.reqRanged.getLocNameStr());

        if(spellPredicate.equals(SpellPredicates.REQUIRE_MELEE))
            return new SText(TextFormatting.RED + "" + TextFormatting.ITALIC + Spells.reqMelee.getLocNameStr());

        if(spellPredicate.equals(SpellPredicates.REQUIRE_STARTER))
            return new SText(TextFormatting.RED + "" + TextFormatting.ITALIC + Spells.reqStarter.getLocNameStr());

        if(spellPredicate.equals(SpellPredicates.REQUIRE_LINKER))
            return new SText(TextFormatting.RED + "" + TextFormatting.ITALIC + Spells.reqLinker.getLocNameStr());

        return new SText("Debug: See uncommon.localization.SpellType.getSpellPreText method");
    }
}
