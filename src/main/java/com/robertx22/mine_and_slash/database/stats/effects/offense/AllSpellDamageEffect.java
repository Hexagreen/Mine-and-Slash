package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseStatEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;

public class AllSpellDamageEffect extends BaseStatEffect<SpellDamageEffect> {
    public static final AllSpellDamageEffect INSTANCE = new AllSpellDamageEffect();

    private AllSpellDamageEffect() {
        super(SpellDamageEffect.class);
    }

    @Override
    public int GetPriority() {
        return Priority.Second.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public SpellDamageEffect activate(SpellDamageEffect effect, StatData data, Stat stat) {
        effect.number *= data.getMultiplier();

        return effect;
    }

    @Override
    public boolean canActivate(SpellDamageEffect effect, StatData data, Stat stat) {
        return effect.getEffectType()
            .equals(EffectData.EffectTypes.SPELL) || effect.getEffectType()
                .equals(EffectData.EffectTypes.ATTACK_SPELL);
    }

}