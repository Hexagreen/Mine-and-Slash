package com.robertx22.mine_and_slash.database.spells.synergies.divine;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.PurifyingFiresSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.divine.JudgementEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SynergyDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class PurifyingFiresJudgementSynergy extends OnDamageDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Hits have a chance to apply: " + JudgementEffect.INSTANCE.locNameForLangFile()));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 4, 7);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.CHANCE, 15, 30);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public void tryActivate(SpellDamageEffect ctx) {

        if (PotionEffectUtils.has(ctx.target, JudgementEffect.INSTANCE)) {
            if (RandomUtils.roll(getContext(ctx.source).getConfigFor(this)
                    .get(SC.CHANCE)
                    .get(Load.spells(ctx.source), this))) {
                PotionEffectUtils.apply(JudgementEffect.INSTANCE, ctx.source, ctx.target);
            }
        }
    }

    @Override
    public Place getSynergyPlace() {
        return Place.SECOND;
    }

    @Override
    public IAbility getRequiredAbility() {
        return PurifyingFiresSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Fires of Judgment";
    }

}
