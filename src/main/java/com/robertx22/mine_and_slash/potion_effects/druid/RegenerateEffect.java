package com.robertx22.mine_and_slash.potion_effects.druid;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nature.NatureBalmSpell;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RegenerateEffect extends BasePotionEffect {

    public static final RegenerateEffect INSTANCE = new RegenerateEffect();

    private RegenerateEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(ctx -> {
            if (ctx.entity.world.isRemote) {
                ParticleUtils.spawnParticles(ParticleTypes.HAPPY_VILLAGER, ctx.entity, 5);
            } else {

                ParticleUtils.spawnParticles(ParticleTypes.HAPPY_VILLAGER, ctx.entity, 5);
                SoundUtils.playSound(ctx.entity, SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, 1.0F, 1.0F);

                int num = getCalc(ctx.caster)
                        .getCalculatedValue(ctx.casterData, ctx.spellsCap, getAbilityThatDeterminesLevel());

                SpellUtils.heal(NatureBalmSpell.getInstance(), ctx.entity, num); //proper spell heal method

                /*ResourcesData.Context hp = new ResourcesData.Context(ctx.caster, ctx.entity, ctx.casterData,
                        ctx.entityData, ResourcesData.Type.HEALTH, num,
                        ResourcesData.Use.RESTORE,
                        NatureBalmSpell.getInstance()
                );

                ctx.entityData.modifyResource(hp);*/
            }
            return ctx;
        }, info -> {
            List<ITextComponent> list = new ArrayList<>();
            list.addAll(descLocName(""));
            list.addAll(getCalc(info.player).GetTooltipString(info, Load.spells(info.player), getAbilityThatDeterminesLevel()));
            return list;
        }));
    }

    @Override
    public String GUID() {
        return "self_regen";
    }

    @Override
    public String locNameForLangFile() {
        return "Soothe";
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return NatureBalmSpell.getInstance();
    }

    @Override
    public Masteries getMastery() {
        if (getSpell() != null) {
            return getSpell().getMastery();
        } else {
            return null;
        }
    }
}
