package com.robertx22.mine_and_slash.database.spells.entities.cloud;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseCloudEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class BlizzardEntity extends BaseCloudEntity {

    public BlizzardEntity(World world) {
        super(EntityRegister.BLIZZARD, world);
    }

    public BlizzardEntity(EntityType type, World world) {
        super(type, world);
    }

    public BlizzardEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.BLIZZARD, world);
    }

    @Override
    public void initSpellEntity() {

    }

    @Override
    public void onHit(LivingEntity entity) {

        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0f, 1.0f);

        DamageEffect dmg = dealSpellDamageTo(entity, new Options().knockbacks(false)
            .activatesEffect(false));

        dmg.Activate();

        SoundUtils.playSound(this, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);

    }

    @Override
    public void summonFallParticle(Vec3d p) {
        ParticleUtils.spawn(ParticleTypes.ITEM_SNOWBALL, world, p);
        ParticleUtils.spawn(ParticleTypes.ITEM_SNOWBALL, world, p);
    }

}