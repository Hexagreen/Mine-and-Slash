package com.robertx22.mine_and_slash.database.spells.synergies.hunting;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.RecoilShotSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnAttackSpellDmgDoneSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ranger.HunterInstinctEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RecoilAddHunterSynergy extends OnAttackSpellDmgDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.Synergy.getLocNameStr()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + Spells.Modifies.getLocNameStr() + getRequiredAbility().getLocName().getString()));

        TooltipUtils.addEmpty(list);

        list.addAll(descLocName(""));
        list.add(new StringTextComponent(HunterInstinctEffect.getInstance().locNameForLangFile()));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 1);
        c.set(SC.ENERGY_COST, 2, 2);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.AMOUNT, 1, 1);
        c.setMaxLevel(1);
        return c;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return RecoilShotSpell.getInstance();
    }

    @Override
    public void tryActivate(AttackSpellDamageEffect ctx) {

        float stacks = get(ctx.source, SC.AMOUNT);

        for (int i = 0; i < stacks; i++) {
            PotionEffectUtils.reApplyToSelf(HunterInstinctEffect.getInstance(), ctx.source);
        }
    }

    @Override
    public String locNameForLangFile() {
        return "Recoil of the Hunter";
    }
}
