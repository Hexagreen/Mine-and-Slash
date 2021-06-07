package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types;

import com.google.common.base.Preconditions;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;

public class CastAoeAlliesEffect extends SpellCastType {
    @Override
    public boolean cast(SpellCastContext ctx) {
        try {

            Preconditions.checkNotNull(ctx.spell.getImmutableConfigs()
                .potionEffect());

            float RADIUS = ctx.getConfigFor(ctx.spell)
                .get(SC.RADIUS)
                .get(ctx.spellsCap, ctx.spell);

            EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector())
                .radius(RADIUS)
                .searchFor(EntityFinder.SearchFor.ALLIES)
                .build()
                .forEach(x -> PotionEffectUtils.apply(ctx.spell.getImmutableConfigs()
                    .potionEffect(), ctx.caster, x));

            SoundUtils.playSound(ctx.caster, ctx.spell.getImmutableConfigs()
                .sound(), 1, 1);

            ctx.spell.spawnParticles(ctx);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
