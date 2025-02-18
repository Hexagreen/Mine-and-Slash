package com.robertx22.mine_and_slash.database.spells.spell_classes.fire;

import com.robertx22.mine_and_slash.database.spells.entities.proj.ThrowFlameEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellPredicates;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.SpellType;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThrowFlamesSpell extends BaseSpell {

    private ThrowFlamesSpell() {
        super(
                new ImmutableSpellConfigs() {

                    @Override
                    public Masteries school() {
                        return Masteries.FIRE;
                    }

                    @Override
                    public SpellCastType castType() {
                        return SpellCastType.PROJECTILE;
                    }

                    @Override
                    public SoundEvent sound() {
                        return SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE;
                    }

                    @Override
                    public Elements element() {
                        return Elements.Fire;
                    }
                }.cooldownIfCanceled(true)
                        .summonsEntity(w -> new ThrowFlameEntity(w))
                        .setSwingArmOnCast().rightClickFor(AllowedAsRightClickOn.MELEE_WEAPON).addCastRequirement(SpellPredicates.REQUIRE_MELEE));
    }

    public static ThrowFlamesSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 9, 14);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.ENERGY_COST, 3, 5);
        c.set(SC.BASE_VALUE, 1, 4);
        c.set(SC.ATTACK_SCALE_VALUE, 0.6F, 1.05F);
        c.set(SC.SHOOT_SPEED, 1.0F, 1.25F);
        c.set(SC.PROJECTILE_COUNT, 3, 3);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_SECONDS, 3, 3);
        c.set(SC.TIMES_TO_CAST, 1, 1);
        c.set(SC.BONUS_HEALTH, 0, 0);
        c.set(SC.DURATION_TICKS, 60, 60);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(7, 4);
    }

    @Override
    public String GUID() {
        return "throw_flames";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.AttackSpell.getLocNameStr()));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + Spells.AttackSpellDesc.getLocNameStr()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + SpellType.getSpellTypeStr(Spells.Projectile)));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(TextFormatting.GRAY + Words.Wep2Fir.locName().getString()));

        TooltipUtils.addEmpty(list);
        list.addAll(descLocName(""));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ThrowFlames;
    }

    private static class SingletonHolder {
        private static final ThrowFlamesSpell INSTANCE = new ThrowFlamesSpell();
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        if (ctx.caster instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) ctx.caster;
            player.spawnSweepParticles();
        }

        ctx.caster.world.playSound((PlayerEntity) null, ctx.caster.getPosX(), ctx.caster.getPosY(), ctx.caster.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0F, 1.0F);

    }
}

