package com.robertx22.mine_and_slash.database.spells.synergies.nature;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nature.NatureBalmSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnSpellCastSynergy;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.RegenerateEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class RegenerateAoeSynergy extends OnSpellCastSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + Spells.Synergy.getLocNameStr()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + Spells.Modifies.getLocNameStr() + getRequiredAbility().getLocName().getString()));

        TooltipUtils.addEmpty(list);

        list.addAll(descLocName(""));
        list.add(new StringTextComponent( RegenerateEffect.INSTANCE.locNameForLangFile()));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.SECOND;
    }

    @Override
    public BaseSpell getRequiredAbility() {
        return NatureBalmSpell.getInstance();
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 2, 3);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.RADIUS, 3F, 6F);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public void tryActivate(SpellCastContext ctx) {
        float radius = this.getPreCalcConfig()
            .get(SC.RADIUS)
            .get(ctx.spellsCap, this);

        BlockPos pos = ctx.caster.getPosition();

        ParticleEnum.sendToClients(pos, ctx.caster.world,
            new ParticlePacketData(pos, ParticleEnum.NOVA).radius(radius)
                .motion(new Vec3d(0, 0, 0))
                .type(ParticleTypes.HAPPY_VILLAGER)
                .amount(50)
        );

        EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector())
            .radius(radius)
            .searchFor(EntityFinder.SearchFor.ALLIES)
            .build()
            .forEach(x -> PotionEffectUtils.apply(RegenerateEffect.INSTANCE, ctx.caster, x));
    }

    @Override
    public String locNameForLangFile() {
        return "Spread Balm";
    }
}
