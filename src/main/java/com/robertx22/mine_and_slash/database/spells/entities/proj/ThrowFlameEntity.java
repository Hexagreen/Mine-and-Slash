package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class ThrowFlameEntity extends BaseElementalBoltEntity {

    public ThrowFlameEntity(EntityType<? extends ThrowFlameEntity> type, World world) {
        super(type, world);
    }

    public ThrowFlameEntity(World worldIn) {
        super(EntityRegister.THROW_FLAMES, worldIn);
    }

    public ThrowFlameEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.THROW_FLAMES, world);
    }

    @Override
    public void initSpellEntity() {
        this.setNoGravity(true);
        this.setDeathTime(60);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.BLAZE_POWDER);
    }

    @Override
    public Elements element() {
        return Elements.Fire;
    }

    @Override
    public void onHit(LivingEntity entity) {
        dealAttackSpellDamageTo(entity);
        entity.playSound(SoundEvents.ENTITY_GENERIC_BURN, 1F, 1F);
    }

    LivingEntity target;

    @Override
    public void onTick() {
        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 5; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.1F);
                    ParticleUtils.spawn(ParticleTypes.FLAME, world, p);
                }
            }

        }
    }

}
