package com.robertx22.mine_and_slash.database.spells.spell_classes;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;

public class SpellTooltips {

    public static ITextComponent singleTargetProjectile() {
        return new StringTextComponent(Spells.Single_Target_Proj.getLocNameStr());
    }

    public static ITextComponent buff() {
        return new StringTextComponent(Spells.Self_Buff.getLocNameStr());
    }
}
