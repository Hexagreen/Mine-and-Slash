package com.robertx22.mine_and_slash.database.spells.synergies.storm;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.ThunderspearSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.StaticEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ThunderSpearLightningStrikeSynergy extends OnDamageDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.Synergy.getLocNameStr()+ " (Bolt)"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + Spells.Modifies.getLocNameStr() + getRequiredAbility().getLocName().getString()));


        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Bolt damage is a special damage type and is"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "unaffected by spell damage modifiers."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Bolt damage is a special damage type and is"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "unaffected by spell damage modifiers."));
        TooltipUtils.addEmpty(list);

        list.addAll(descLocName(""));


        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 2);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 6, 12);
        c.setMaxLevel(4);
        return c;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.SECOND;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return ThunderspearSpell.getInstance();
    }

    @Override
    public void tryActivate(SpellDamageEffect ctx) {
        if (PotionEffectUtils.has(ctx.target, StaticEffect.INSTANCE)) {

            PotionEffectUtils.reduceStacks(ctx.target, StaticEffect.INSTANCE);

            SpellUtils.summonLightningStrike(ctx.target);

            SoundUtils.playSound(ctx.target, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 0.75F, 1);

            /*int num = getCalcVal(ctx.source);

            getSynergyDamage(ctx, num).Activate();*/

            int num = getPreCalcConfig().getCalc(Load.spells(ctx.source), this)
                    .getCalculatedValue(ctx.sourceData, Load.spells(ctx.source), this);

            DamageEffect dmg = new DamageEffect(
                    null, ctx.source, ctx.target, num, EffectData.EffectTypes.BOLT, WeaponTypes.None);
            dmg.element = getSpell()
                    .getElement();
            dmg.Activate();

        }
    }

    @Override
    public String locNameForLangFile() {
        return "Spear Lightning";
    }
}
