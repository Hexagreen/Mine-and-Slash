package com.robertx22.mine_and_slash.database.spells.synergies.unholy;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.AnimateScytheSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.ChillingTouchSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnAttackSpellDmgDoneSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.necromancer.CrippleEffect;
import com.robertx22.mine_and_slash.potion_effects.necromancer.NecroticTetherEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AnimateScytheNecroticSynergy extends OnAttackSpellDmgDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.Synergy.locName().getString()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Animate Scythe"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(Spells.HitApplyEffect.locName().getString() + NecroticTetherEffect.INSTANCE.locNameForLangFile()));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.CHANCE, 25, 100);
        c.setMaxLevel(8);
        return c;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return AnimateScytheSpell.getInstance();
    }

    @Override
    public void tryActivate(AttackSpellDamageEffect ctx) {
        if (RandomUtils.roll(getContext(ctx.source).getConfigFor(this)
                .get(SC.CHANCE)
                .get(Load.spells(ctx.source), this))) {
            PotionEffectUtils.reApplyToSelf(NecroticTetherEffect.INSTANCE, ctx.source);
        }
    }

    @Override
    public String locNameForLangFile() {
        return "Necrotic Scythe";
    }
}
