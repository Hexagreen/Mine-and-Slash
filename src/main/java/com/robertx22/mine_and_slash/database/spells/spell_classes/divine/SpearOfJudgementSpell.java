package com.robertx22.mine_and_slash.database.spells.spell_classes.divine;

import com.robertx22.mine_and_slash.database.spells.entities.trident.SpearOfJudgementEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.divine.JudgementEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class SpearOfJudgementSpell extends BaseSpell {

    private SpearOfJudgementSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.DIVINE;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.BLOCK_FIRE_EXTINGUISH;
                }

                @Override
                public Elements element() {
                    return Elements.Thunder;
                }
            }.summonsEntity(x -> new SpearOfJudgementEntity(x))
                .setSwingArmOnCast()
        );
    }

    public static SpearOfJudgementSpell getInstance() {
        return SpearOfJudgementSpell.SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 12, 18);
        c.set(SC.BASE_VALUE, 6, 15);
        c.set(SC.ATTACK_SCALE_VALUE, 0.5F, 0.75F);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_SECONDS, 14, 10);
        c.set(SC.TIMES_TO_CAST, 1, 1);
        c.set(SC.SHOOT_SPEED, 1.8F, 3.0F);
        c.set(SC.DURATION_TICKS, 100, 120);
        c.set(SC.PROJECTILE_COUNT, 1, 1);
        c.set(SC.TICK_RATE, 10, 10);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(3, 5);
    }

    @Override
    public String GUID() {
        return "spear_of_judgement";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Converts Weapon DMG to Thunder and"));
        list.add(new StringTextComponent("throws out a spear that deals damage"));
        list.add(new StringTextComponent("which applies Judgment: "));

        list.addAll(JudgementEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.SpearOfJudgement;
    }

    private static class SingletonHolder {
        private static final SpearOfJudgementSpell INSTANCE = new SpearOfJudgementSpell();
    }
}