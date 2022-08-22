package com.robertx22.mine_and_slash.database.spells.synergies.nature;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nature.RockSlideSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.ThunderstormSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.base.Synergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RockSlideEnhancedSynergy extends OnDamageDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.Synergy.getLocName()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + Spells.Modifies.getLocName() + getRequiredAbility().getLocName().getString()));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + Words.Wep2Nat.locName().getString()));
        TooltipUtils.addEmpty(list);

        list.addAll(descLocName(""));
        list.add(new StringTextComponent(ThornsEffect.INSTANCE.locNameForLangFile()));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public void tryActivate(SpellDamageEffect ctx) {
        if (PotionEffectUtils.has(ctx.target, ThornsEffect.INSTANCE)) {
            if (PotionEffectUtils.getStacks(ctx.target, ThornsEffect.INSTANCE) >= 6) {
            /*int dmg = getCalc(Load.spells(ctx.source)).getCalculatedValue(ctx.sourceData, Load.spells(ctx.source), this);

            getSynergyDamage(ctx, dmg)
                .Activate();*/

                int num = getPreCalcConfig().getCalc(Load.spells(ctx.source), this)
                        .getCalculatedValue(ctx.sourceData, Load.spells(ctx.source), this);

                DamageEffect dmg = new DamageEffect(
                        null, ctx.source, ctx.target, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
                dmg.element = getSpell()
                        .getElement();
                dmg.Activate();
            }
        }
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 3, 5);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 2, 5);
        c.set(SC.ATTACK_SCALE_VALUE, 0.2F, 0.3F);
        c.setMaxLevel(8);
        return c;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return RockSlideSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Virulent Shrapnel";
    }
}
