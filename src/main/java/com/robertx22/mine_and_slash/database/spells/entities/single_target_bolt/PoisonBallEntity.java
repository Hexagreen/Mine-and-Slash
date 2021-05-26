package com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class PoisonBallEntity extends BaseElementalBoltEntity {

    public PoisonBallEntity(EntityType<? extends PoisonBallEntity> type, World world) {
        super(type, world);
    }

    public PoisonBallEntity(World worldIn) {

        super(EntityRegister.POISON_BALL, worldIn);

    }

    public PoisonBallEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.POISON_BALL, world);
    }

    @Override
    public void initSpellEntity() {
        this.setNoGravity(true);
        this.setDeathTime(60);
    }

    @Override
    public Elements element() {
        return Elements.Nature;
    }

    @Override
    public void onHit(LivingEntity entity) {
        dealSpellDamageTo(entity);

        SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_HURT, 0.8F, 1F);
    }

    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 3; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.25F);
                    ParticleUtils.spawn(ParticleTypes.ITEM_SLIME, world, p);
                }
            }
        }

    }

}