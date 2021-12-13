package com.robertx22.mine_and_slash.database.spells.entities.bases;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class BaseElementalBoltEntity extends EntityBaseProjectile {

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getItem() {
        return new ItemStack(this.element().projectileItem);
    }

    public abstract Elements element();

    @Override
    public void initSpellEntity() {
        this.setNoGravity(true);
        this.setDeathTime(60);
    }

    public BaseElementalBoltEntity(EntityType<? extends Entity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public double radius() {
        return 0.5D;
    }

    public abstract void onHit(LivingEntity entity);

    @Override
    protected void onImpact(RayTraceResult result) {

        LivingEntity entityHit = getEntityHit(result, 0.3D);

        if (entityHit != null) {
            if (world.isRemote) {
                this.playSound(SoundEvents.ENTITY_GENERIC_HURT, 1F, 0.9F);
            }

            onHit(entityHit);

        } else {
            if (world.isRemote) {
                this.playSound(SoundEvents.BLOCK_STONE_HIT, 0.7F, 0.9F);
            }
        }

        this.remove();
    }

}