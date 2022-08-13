package com.robertx22.mine_and_slash.database.spells.spell_classes.unholy;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellPredicates;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.SpellType;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoneSquallSpell extends BaseSpell {

    private BoneSquallSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.UNHOLY;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SPECIAL;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }

            }.cooldownIfCanceled(true).setSwingArmOnCast().addCastRequirement(SpellPredicates.REQUIRE_MELEE));
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0.6F, 0.09F);
        c.set(SC.MANA_COST, 2, 3);
        c.set(SC.ENERGY_COST, 6, 9);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.HEALTH_ATTACK_SCALE_VALUE, 0.06F, 0.18F);
        c.set(SC.CAST_TIME_TICKS, 30, 30);
        c.set(SC.COOLDOWN_SECONDS, 6, 3);
        c.set(SC.RADIUS, 2, 4);
        c.set(SC.TIMES_TO_CAST, 4, 6);

        c.setMaxLevel(8);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(6, 5);
    }

    public static BoneSquallSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "bone_squall";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.AttackSpell.locName().getString()));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + Spells.AttackSpellDesc.locName().getString()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + SpellType.getSpellTypeStr(Arrays.asList(Spells.Area, Spells.Channel))));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Health to Phys DMG."));
        TooltipUtils.addEmpty(list);
        list.add(new SText("Expend your own health and send out a flurry"));
        list.add(new SText("of bones to damage enemies around you: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    public void damageMobsAroundYou(SpellCastContext ctx, LivingEntity caster) {

        if (!caster.world.isRemote) {

            float radius = ctx.getConfigFor(this)
                .get(SC.RADIUS)
                .get(ctx.spellsCap, this);

            ParticlePacketData pdata = new ParticlePacketData(caster.getPosition()
                .up(1), ParticleEnum.WHIRLWIND);
            pdata.radius = radius;
            ParticleEnum.WHIRLWIND.sendToClients(caster, pdata);

            int num = getCalculation(ctx).getCalculatedValue(Load.Unit(caster), ctx.spellsCap, ctx.ability);

            List<LivingEntity> entities = EntityFinder.start(caster, LivingEntity.class, caster.getPositionVector())
                .radius(radius)
                .build();

            for (LivingEntity en : entities) {
                AttackSpellDamageEffect dmg = new AttackSpellDamageEffect(ctx.caster, en, num, ctx.data, Load.Unit(en),
                        this
                );
                dmg.removeKnockback();
                dmg.Activate();

            }
        }
    }

    @Override
    public Words getName() {
        return Words.BoneSquall;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        damageMobsAroundYou(ctx, ctx.caster);

        SoundUtils.playSound(ctx.caster, SoundEvents.ENTITY_SKELETON_HURT, 0.6F, 1.2F);

    }

    private static class SingletonHolder {
        private static final BoneSquallSpell INSTANCE = new BoneSquallSpell();
    }
}
