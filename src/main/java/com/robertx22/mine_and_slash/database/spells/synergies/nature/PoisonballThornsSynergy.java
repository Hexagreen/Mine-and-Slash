package com.robertx22.mine_and_slash.database.spells.synergies.nature;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nature.PoisonBallSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PoisonballThornsSynergy extends OnDamageDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Hits have a chance to apply: " + ThornsEffect.INSTANCE.locNameForLangFile()));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public void tryActivate(SpellDamageEffect effect) {

        float chance = getContext(effect.source).getConfigFor(this)
                .get(SC.CHANCE)
                .get(Load.spells(effect.source), this);

        if (RandomUtils.roll(chance)) {
            PotionEffectUtils.apply(ThornsEffect.INSTANCE, effect.source, effect.target);

        }
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 2);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.CHANCE, 60, 100);
        c.setMaxLevel(6);
        return c;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return PoisonBallSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Toxin";
    }
}
