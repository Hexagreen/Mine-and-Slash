package com.robertx22.mine_and_slash.database.spells.spell_classes.nature;

import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.druid.VenomLoadEffect;
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

public class VenomLoadSpell extends BaseSpell {

    private VenomLoadSpell() {
        super(new ImmutableSpellConfigs() {
            @Override
            public Masteries school() {
                return Masteries.NATURE;
            }

            @Override
            public SpellCastType castType() {
                return SpellCastType.GIVE_EFFECT;
            }

            @Override
            public SoundEvent sound() {
                return SoundEvents.ENTITY_SPLASH_POTION_BREAK;
            }

            @Override
            public Elements element() {
                return Elements.Nature;
            }
        }.addsEffect(VenomLoadEffect.INSTANCE)
            .setSwingArmOnCast());
    }

    public static VenomLoadSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 6, 10);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.RADIUS, 2, 5);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 0.25F, 0.25F);
        c.set(SC.HEALTH_ATTACK_SCALE_VALUE, 0.4F, 0.4F);
        c.set(SC.CAST_TIME_TICKS, 60, 20);
        c.set(SC.COOLDOWN_SECONDS, 60, 60);
        c.set(SC.DURATION_TICKS, 30 * 20, 30 * 20);
        c.set(SC.TICK_RATE, 10, 10);

        c.setMaxLevel(4);
        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(1, 2);
    }

    @Override
    public String GUID() {
        return "venom_load";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.NormalSpell.getLocNameStr()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + SpellType.getSpellTypeStr(Arrays.asList(Spells.Area, Spells.Duration, Spells.Self, Spells.UniqueBuff))));

        TooltipUtils.addEmpty(list);

        list.add(SpellTooltips.buff());

        list.addAll(VenomLoadEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.VenomLoad;
    }

    private static class SingletonHolder {
        private static final VenomLoadSpell INSTANCE = new VenomLoadSpell();
    }
}
