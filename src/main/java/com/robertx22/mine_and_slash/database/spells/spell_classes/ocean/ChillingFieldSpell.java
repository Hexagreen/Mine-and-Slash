package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean;

import com.robertx22.mine_and_slash.database.spells.entities.cloud.ChillingFieldEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.SpellType;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChillingFieldSpell extends BaseSpell {

    private ChillingFieldSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.OCEAN;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.AT_SIGHT;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_INSIDE;
                }

                @Override
                public Elements element() {
                    return Elements.Water;
                }
            }.summonsEntity(w -> new ChillingFieldEntity(w))
                .setSwingArmOnCast());
    }

    public static ChillingFieldSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 10, 14);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 2, 3.5F);
        c.set(SC.MANA_ATTACK_SCALE_VALUE, 0.06F, 0.12F);
        c.set(SC.CAST_TIME_TICKS, 20, 20);
        c.set(SC.COOLDOWN_SECONDS, 16, 13);
        c.set(SC.TICK_RATE, 40, 30);
        c.set(SC.RADIUS, 2, 4);
        c.set(SC.DURATION_TICKS, 260, 260);
        c.set(SC.BONUS_HEALTH, 0, 0);

        c.setMaxLevel(12);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(5, 1);
    }

    @Override
    public String GUID() {
        return "chilling_field";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.NormalSpell.getLocNameStr()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + SpellType.getSpellTypeStr(Spells.Area, Spells.Duration, Spells.Storm)));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + Words.Mana2Fro.locName().getString()));
        TooltipUtils.addEmpty(list);
        list.addAll(descLocName(""));
        list.add(new StringTextComponent(FrostEffect.INSTANCE.locNameForLangFile()));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ChillingField;
    }

    private static class SingletonHolder {
        private static final ChillingFieldSpell INSTANCE = new ChillingFieldSpell();
    }
}

