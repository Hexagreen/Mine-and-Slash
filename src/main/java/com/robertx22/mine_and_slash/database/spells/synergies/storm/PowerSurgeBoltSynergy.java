package com.robertx22.mine_and_slash.database.spells.synergies.storm;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.PowerSurgeSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnHitSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.PowerSurgeEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class PowerSurgeBoltSynergy extends OnHitSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.Synergy.getLocNameStr()+ Spells.Bolt.getLocNameStr()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + Spells.Modifies.getLocNameStr() + getRequiredAbility().getLocName().getString()));

        TooltipUtils.addEmpty(list);
        list.addAll(Spells.BoltDesc.longDesc(TextFormatting.GRAY));
        TooltipUtils.addEmpty(list);

        list.addAll(descLocName(""));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.CAST_TIME_TICKS, 20, 20);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 2, 4);
        c.set(SC.CHANCE, 5F, 20F);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public void tryActivate(DamageEffect ctx) {

        //int chance = RandomUtils.RandomRange(1,4);

        float chance = getContext(ctx.source).getConfigFor(this)
                .get(SC.CHANCE)
                .get(Load.spells(ctx.source), this);

        if (PotionEffectUtils.has(ctx.source, PowerSurgeEffect.INSTANCE)) {

            if (RandomUtils.roll(chance) && ctx.getEffectType() == EffectData.EffectTypes.SPELL) {

                int num = getPreCalcConfig().getCalc(Load.spells(ctx.source), this)
                        .getCalculatedValue(ctx.sourceData, Load.spells(ctx.source), this);

                //SoundUtils.playSound(ctx.target, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8F, 2);

                SpellUtils.summonLightningStrike(ctx.target);

                SoundUtils.playSound(ctx.target, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 0.5F, 1);

                DamageEffect dmg = new DamageEffect(
                        null, ctx.source, ctx.target, num, EffectData.EffectTypes.BOLT, WeaponTypes.None);
                dmg.element = Elements.Thunder;
                dmg.Activate();

            }
        }
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public IAbility getRequiredAbility() {
        return PowerSurgeSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Stray Currents";
    }
}
