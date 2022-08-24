package com.robertx22.mine_and_slash.database.spells.spell_classes.nature;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.PetrifyEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.SpellType;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GorgonsGazeSpell extends BaseSpell {

    private GorgonsGazeSpell() {
        super(new ImmutableSpellConfigs() {
            @Override
            public Masteries school() {
                return Masteries.NATURE;
            }

            @Override
            public SpellCastType castType() {
                return SpellCastType.SPECIAL;
            }

            @Override
            public SoundEvent sound() {
                return SoundEvents.ENTITY_CREEPER_PRIMED;
            }

            @Override
            public Elements element() {
                return Elements.Nature;
            }
        }.setSwingArmOnCast());
    }

    public static GorgonsGazeSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(7, 4);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 14, 22);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 12, 24);
        c.set(SC.ATTACK_SCALE_VALUE, 1.4F, 2F);
        c.set(SC.SHOOT_SPEED, 0.8F, 1.2F);
        c.set(SC.CAST_TIME_TICKS, 10, 5);
        c.set(SC.COOLDOWN_SECONDS, 18, 12);
        c.set(SC.DURATION_TICKS, 80, 140);
        c.set(SC.TICK_RATE, 20, 20);
        c.set(SC.RADIUS, 15, 15);

        c.setMaxLevel(8);

        return c;
    }

    @Override
    public String GUID() {
        return "gorgons_gaze";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.NormalSpell.getLocName()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + SpellType.getSpellTypeStr(Arrays.asList(Spells.Area, Spells.Debuff, Spells.Duration))));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Weapon DMG to Nature DMG."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent("Damages all enemies in front of you and turn"));
        list.add(new StringTextComponent("them into stone:"));


        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));
        list.add(new StringTextComponent("Applies: "));

        list.addAll(PetrifyEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.GorgonsGaze;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        float RADIUS = ctx.getConfigFor(this)
                .get(SC.RADIUS)
                .get(ctx.spellsCap, this);

        LivingEntity caster = ctx.caster;

        World world = caster.world;

        SoundUtils.playSound(caster, ModSounds.STONE_CRACK.get(), 1, 1);

        List<LivingEntity> entities = EntityFinder.start(caster, LivingEntity.class, caster.getPositionVector())
                .radius(RADIUS * 0.2F)
                .distance(RADIUS)
                .finder(EntityFinder.Finder.IN_FRONT)
                .searchFor(EntityFinder.SearchFor.ENEMIES)
                .build();

        int num = ctx.getConfigFor(this)
                .getCalc(ctx.spellsCap, this)
                .getCalculatedValue(ctx.data, ctx.spellsCap, this);

        for (LivingEntity en : entities) {

            SpellDamageEffect dmg = new SpellDamageEffect(ctx.caster, en, num, ctx.data, Load.Unit(en),
                    this
            );
            dmg.Activate();
            PotionEffectUtils.apply(PetrifyEffect.INSTANCE, caster, en);
        }

        EntityFinder.start(caster, LivingEntity.class, caster.getPositionVector())
            .radius(RADIUS * 0.2F)
            .distance(RADIUS)
            .finder(EntityFinder.Finder.IN_FRONT)
            .build()
            .forEach(x -> PotionEffectUtils.apply(PetrifyEffect.INSTANCE, caster, x));

    }

    private static class SingletonHolder {
        private static final GorgonsGazeSpell INSTANCE = new GorgonsGazeSpell();
    }
}
