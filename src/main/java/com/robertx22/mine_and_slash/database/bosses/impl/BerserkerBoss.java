package com.robertx22.mine_and_slash.database.bosses.impl;

import com.robertx22.mine_and_slash.database.bosses.base.Boss;
import com.robertx22.mine_and_slash.database.bosses.base.BossData;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;

public class BerserkerBoss extends Boss {

    private BerserkerBoss() {
    }

    public static BerserkerBoss getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public IParticleData getParticle() {
        return ParticleTypes.WITCH;
    }

    @Override
    public ITextComponent getName() {
        return new SText("Berserker");
    }

    @Override
    public void onHealthTreshholdTriggered(LivingEntity en, BossData.HealthTreshhold t) {
        if (t == BossData.HealthTreshhold.T_75) {
            en.addPotionEffect(new EffectInstance(Effects.SPEED, 300, 1));
        } else if (t == BossData.HealthTreshhold.T_50) {
            en.addPotionEffect(new EffectInstance(Effects.SPEED, 500, 1));
        } else if (t == BossData.HealthTreshhold.T_25) {

            en.addPotionEffect(new EffectInstance(Effects.SPEED, 3000, 1));
        } else if (t == BossData.HealthTreshhold.T_10) {

            en.addPotionEffect(new EffectInstance(Effects.SPEED, 3000, 2));

        }
    }

    @Override
    public String GUID() {
        return "berserker";
    }

    private static class SingletonHolder {
        private static final BerserkerBoss INSTANCE = new BerserkerBoss();
    }
}