package com.robertx22.mine_and_slash.database.spells.synergies.hunting;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.ArrowStormSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.DoubleShotSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnAttackSpellDmgDoneSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ranger.WoundsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ArrowStormAddWoundsSynergy extends OnAttackSpellDmgDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.Synergy.locName().getString()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Arrow Storm"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(Spells.HitApplyEffect.locName().getString() + WoundsEffect.getInstance().locNameForLangFile()));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public void tryActivate(AttackSpellDamageEffect effect) {

        float chance = getContext(effect.source).getConfigFor(this)
                .get(SC.CHANCE)
                .get(Load.spells(effect.source), this);

        if (RandomUtils.roll(chance)) {
            PotionEffectUtils.apply(WoundsEffect.getInstance(), effect.source, effect.target);

        }
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 2);
        c.set(SC.ENERGY_COST, 1, 5);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.CHANCE, 5, 25);
        c.setMaxLevel(8);
        return c;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return ArrowStormSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Serrated Storm";
    }
}
