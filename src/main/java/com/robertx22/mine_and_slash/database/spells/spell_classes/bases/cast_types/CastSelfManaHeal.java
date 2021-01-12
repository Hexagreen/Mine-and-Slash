package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;

public class CastSelfManaHeal extends SpellCastType {

    @Override
    public boolean cast(SpellCastContext ctx) {

        SpellUtils.healCasterMana(ctx);

        if (ctx.spell.getImmutableConfigs()
            .sound() != null) {
            SoundUtils.playSound(ctx.caster, ctx.spell.getImmutableConfigs()
                .sound(), 1, 1);
        }
        return true;
    }
}
