package com.robertx22.mine_and_slash.database.spells.spell_classes.storm;

import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.shaman.CriticalSurgeEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.SpellType;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalSurgeSpell extends BaseSpell {

    private CriticalSurgeSpell() {
        super(
            new ImmutableSpellConfigs() {
                @Override
                public Masteries school() {
                    return Masteries.STORM;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.GIVE_EFFECT;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER;
                }

                @Override
                public Elements element() {
                    return Elements.Thunder;
                }
            }.addsEffect(CriticalSurgeEffect.INSTANCE)
                .setSwingArmOnCast());

    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 24, 30);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.CAST_TIME_TICKS, 120, 80);
        c.set(SC.COOLDOWN_SECONDS, 10, 10);
        c.set(SC.DURATION_TICKS, 20 * 120, 20 * 180);

        c.setMaxLevel(8);
        return c;
    }

    public static CriticalSurgeSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "critical_surge";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.NormalSpell.getLocNameStr()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + SpellType.getSpellTypeStr(Spells.Buff, Spells.Self)));

        TooltipUtils.addEmpty(list);

        list.add(SpellTooltips.buff());
        list.addAll(CriticalSurgeEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));
        list.addAll(descLocName("", TextFormatting.RED));

        return list;

    }

    @Override
    public Words getName() {
        return Words.CriticalSurge;
    }

    @Override
    public void spawnParticles(SpellCastContext ctx) {
        if (ctx.caster.world.isRemote) {
            ParticleUtils.spawnParticles(ParticleTypes.HAPPY_VILLAGER, ctx.caster, 30);
        }
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(3, 4);
    }

    private static class SingletonHolder {
        private static final CriticalSurgeSpell INSTANCE = new CriticalSurgeSpell();
    }
}
