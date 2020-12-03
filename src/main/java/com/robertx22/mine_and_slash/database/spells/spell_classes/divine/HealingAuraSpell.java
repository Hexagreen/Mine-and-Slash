package com.robertx22.mine_and_slash.database.spells.spell_classes.divine;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class HealingAuraSpell extends BaseSpell {

    private HealingAuraSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.DIVINE;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SELF_HEAL;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE;
                }

                @Override
                public Elements element() {
                    return Elements.Elemental;
                }
            }.cooldownIfCanceled(true)
        );
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        float RADIUS = ctx.getConfigFor(this)
            .get(SC.RADIUS)
            .get(ctx.spellsCap, this);

        List<LivingEntity> list = EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector())
            .finder(EntityFinder.Finder.RADIUS)
            .radius(RADIUS)
            .searchFor(EntityFinder.SearchFor.ALLIES)
            .build();

        for (LivingEntity en : list) {

            int num = ctx.getConfigFor(this)
                .getCalc(ctx.spellsCap, this)
                .getCalculatedValue(ctx.data, ctx.spellsCap, this);

            SpellUtils.heal(this, en, num);

            ParticleEnum.sendToClients(
                en.getPosition(), en.world,
                new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(RADIUS)
                    .motion(new Vec3d(0, 0, 0))
                    .type(ParticleTypes.FALLING_HONEY)
                    .amount((int) (90)));
            ParticleEnum.sendToClients(
                    en.getPosition(), en.world,
                    new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(RADIUS)
                            .motion(new Vec3d(0, 0, 0))
                            .type(ParticleTypes.HEART)
                            .amount((int) (45)));
        }
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 6, 13);
        c.set(SC.BASE_VALUE, 8, 32);
        c.set(SC.CAST_TIME_TICKS, 80, 60);
        c.set(SC.COOLDOWN_SECONDS, 40, 15);
        c.set(SC.TIMES_TO_CAST, 4, 4);
        c.set(SC.RADIUS, 3, 5);

        c.setMaxLevel(12);

        return c;
    }

    public static HealingAuraSpell getInstance() {
        return HealingAuraSpell.SingletonHolder.INSTANCE;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(2, 1);
    }

    @Override
    public String GUID() {
        return "healing_aura";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Heal everyone around you: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.HealingAura;
    }

    private static class SingletonHolder {
        private static final HealingAuraSpell INSTANCE = new HealingAuraSpell();
    }
}

