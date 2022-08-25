package com.robertx22.mine_and_slash.database.spells.spell_classes.physical.linkers;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellPredicates;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.physical.ComboLinkerEffect;
import com.robertx22.mine_and_slash.potion_effects.physical.ComboStarterEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.SpellType;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class SpiritDrainLinkerSpell extends BaseSpell {

    private SpiritDrainLinkerSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.PHYSICAL;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SPECIAL;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_EVOKER_FANGS_ATTACK;
                }

                @Override
                public Elements element() {
                    return Elements.Nature;
                }
            }.cooldownIfCanceled(true)
                .setSwingArmOnCast().addCastRequirement(SpellPredicates.REQUIRE_MELEE).addCastRequirement(SpellPredicates.REQUIRE_STARTER));
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        if (ctx.caster instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) ctx.caster;
            player.spawnSweepParticles();
        }

        ctx.caster.world.playSound((PlayerEntity) null, ctx.caster.getPosX(), ctx.caster.getPosY(), ctx.caster.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0F, 1.0F);

        Vec3d look = ctx.caster.getLookVec()
            .scale(3);

        List<LivingEntity> list = EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector()
            .add(look)
            .add(0, ctx.caster.getHeight() / 2, 0))
            .finder(EntityFinder.Finder.RADIUS).searchFor(EntityFinder.SearchFor.ENEMIES)
            .radius(2)
            .height(2)
            .build();

        SoundUtils.playSound(ctx.caster, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 0.9F, 1.3F);

        int num = ctx.getConfigFor(this)
                .getCalc(ctx.spellsCap, this)
                .getCalculatedValue(ctx.data, ctx.spellsCap, this);

        for (LivingEntity en : list) {

            AttackSpellDamageEffect dmg = new AttackSpellDamageEffect(ctx.caster, en, num, ctx.data, Load.Unit(en),
                this
            );
            dmg.Activate();

            ParticleEnum.sendToClients(
                en.getPosition(), en.world,
                new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                    .motion(new Vec3d(0, 0, 0))
                    .type(ParticleTypes.WITCH)
                    .amount((int) (40)));

        }

        SpellUtils.healCaster(ctx);
        SpellUtils.healCasterMana(ctx);
        SpellUtils.healCasterEnergy(ctx);

        ParticleEnum.sendToClients(
                ctx.caster, new ParticlePacketData(ctx.caster.getPosition(), ParticleEnum.AOE).type(
                        ParticleTypes.HEART)
                        .motion(new Vec3d(0, 0, 0))
                        .amount(10));

        if (PotionEffectUtils.has(ctx.caster, ComboStarterEffect.INSTANCE)) {
            PotionEffectUtils.reduceStacks(ctx.caster, ComboStarterEffect.INSTANCE);
        }
        PotionEffectUtils.reApplyToSelf(ComboLinkerEffect.INSTANCE, ctx.caster);
    }

    public static SpiritDrainLinkerSpell getInstance() {
        return SpiritDrainLinkerSpell.SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 0, 0);
        c.set(SC.ENERGY_COST, 6, 9);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.ATTACK_SCALE_VALUE, 2.75F, 3.75F);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_TICKS, 80, 80);
        c.set(SC.CDR_EFFICIENCY, 0, 0);
        c.set(SC.TIMES_TO_CAST, 1, 1);

        c.setMaxLevel(12);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(2, 4);
    }

    @Override
    public String GUID() {
        return "spirit_drain_linker";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.AttackSpell.getLocNameStr()));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + Spells.AttackSpellDesc.getLocNameStr()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + SpellType.getSpellTypeStr(Spells.Heal, Spells.Melee, Spells.Self)));

        TooltipUtils.addEmpty(list);
        list.addAll(Spells.NotAffectCooldown.longDesc(TextFormatting.GRAY));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + Words.Wep2Nat.locName().getString()));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + Spells.GenerateEffect.getLocNameStr() + ComboLinkerEffect.INSTANCE.locNameForLangFile()));
        list.add(new StringTextComponent(TextFormatting.GRAY + Spells.ExpendEffect.getLocNameStr() + ComboStarterEffect.INSTANCE.locNameForLangFile()));
        TooltipUtils.addEmpty(list);

        list.addAll(descLocName(""));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.SpiritDrainLinker;
    }

    private static class SingletonHolder {
        private static final SpiritDrainLinkerSpell INSTANCE = new SpiritDrainLinkerSpell();
    }
}