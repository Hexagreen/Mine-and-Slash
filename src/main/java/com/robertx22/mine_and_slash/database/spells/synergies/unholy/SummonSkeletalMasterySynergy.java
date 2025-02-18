package com.robertx22.mine_and_slash.database.spells.synergies.unholy;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.SummonSkeletalArmySpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnBasicAttackSynergy;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SummonSkeletalMasterySynergy extends OnBasicAttackSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.Synergy.getLocNameStr()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + Spells.Modifies.getLocNameStr() + getRequiredAbility().getLocName().getString()));

        TooltipUtils.addEmpty(list);

        list.addAll(descLocName(""));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 3);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 2, 11);
        c.set(SC.CHANCE, 15, 75);
        c.setMaxLevel(8);
        return c;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return SummonSkeletalArmySpell.getInstance();
    }

    @Override
    public void tryActivate(DamageEffect ctx) {

        if (RandomUtils.roll(getContext(ctx.source).getConfigFor(this)
                .get(SC.CHANCE)
                .get(Load.spells(ctx.source), this)) && ctx.getEffectType() == EffectData.EffectTypes.SUMMON_DMG) {

            float amount = getCalc(Load.spells(ctx.source)).getCalculatedValue(ctx.sourceData, Load.spells(ctx.source
            ), this);

            SpellUtils.heal(getSpell(), ctx.source, amount);

            ParticleEnum.sendToClients(
                    ctx.source.getPosition(), ctx.source.world,
                    new ParticlePacketData(ctx.source.getPositionVector(), ParticleEnum.AOE).radius(1)
                            .motion(new Vec3d(0, 0, 0))
                            .type(ParticleTypes.HEART)
                            .amount(25));
        }
    }

    @Override
    public String locNameForLangFile() {
        return "Minion Mastery";
    }
}
