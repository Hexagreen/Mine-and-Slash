package com.robertx22.mine_and_slash.database.spells.synergies.ocean;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.HeartOfIceSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnSpellCastSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.ColdEssenceEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class HeartOfIceFrostSynergy extends OnSpellCastSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Consumes Cold Essence stacks to increase heal: "));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 2, 5);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 1, 6);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public BaseSpell getRequiredAbility() {
        return HeartOfIceSpell.getInstance();
    }

    @Override
    public void tryActivate(SpellCastContext ctx) {

        int stacks = PotionEffectUtils.getStacks(ctx.caster, ColdEssenceEffect.INSTANCE);

        if (stacks > 0) {
            PotionEffectUtils.reduceStacks(ctx.caster, ColdEssenceEffect.INSTANCE, 500);
            float amount = ctx.getConfigFor(this)
                .getCalc(ctx.spellsCap, this)
                .getCalculatedValue(Load.Unit(ctx.caster), Load.spells(ctx.caster), this) * stacks;

            SpellUtils.heal(ctx.spell, ctx.caster, amount);

        }

    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public String locNameForLangFile() {
        return "Cold Healing Essence";
    }
}