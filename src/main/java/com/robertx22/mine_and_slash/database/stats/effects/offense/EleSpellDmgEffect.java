package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class EleSpellDmgEffect extends BaseDamageEffect {

    @Override
    public int GetPriority() {
        return Priority.Third.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {
        effect.number *= data.getMultiplier();
        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {

        if (effect.element.equals(Elements.Physical)) {
            return false;
        }

        if (effect.getEffectType()
            .equals(EffectData.EffectTypes.SPELL)) {
            if (stat.getElement() != null && stat.getElement()
                .equals(effect.GetElement())) {
                return true;
            }
            if (effect.element != Elements.Physical && stat.getElement() == Elements.Elemental) {
                return true;
            }
        }

        return false;
    }

}
