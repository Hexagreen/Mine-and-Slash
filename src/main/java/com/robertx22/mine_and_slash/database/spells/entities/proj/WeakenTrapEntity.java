package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityBaseProjectile;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ranger.SnareEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.WeakenEffect;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class WeakenTrapEntity extends EntityBaseProjectile {

    public WeakenTrapEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);
    }

    public WeakenTrapEntity(World worldIn) {
        super(EntityRegister.WEAKEN_TRAP, worldIn);

    }

    public WeakenTrapEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.WEAKEN_TRAP, world);

    }

    @Override
    public double radius() {
        return 3.0F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.TRIPWIRE_HOOK);
    }

    @Override
    public void onTick() {

        EntitySpellData sdata = getSpellData();
        float RADIUS = sdata.configs.get(SC.RADIUS);

        if (this.inGround || this.ticksExisted % 5 == 0) {
            if (!world.isRemote) {
                LivingEntity caster = getCaster();

                if (caster == null) {
                    return;
                }

                List<LivingEntity> entities = EntityFinder.start(caster, LivingEntity.class, getPositionVector())
                    .radius(RADIUS)
                    .build();

                if (entities.size() > 0) {

                    this.remove();

                    SoundUtils.playSound(this, SoundEvents.ENTITY_SPLASH_POTION_BREAK, 1, 1);
                    Vec3d p = GeometryUtils.getRandomHorizontalPosInRadiusCircle(
                            getPositionVector().add(0, 0.2F, 0), RADIUS);
                    ParticleUtils.spawn(ParticleTypes.CLOUD, world, p);

                    entities.forEach(x -> {

                        PotionEffectUtils.apply(WeakenEffect.INSTANCE, getCaster(), x);
                    });

                }
            }
        }

        if (world.isRemote) {

            if (ticksExisted % 2 == 0) {
                for (int i = 0; i < 10; i++) {
                    Vec3d p = GeometryUtils.getRandomHorizontalPosInRadiusCircle(
                        getPositionVector().add(0, 0.2F, 0), RADIUS);
                    ParticleUtils.spawn(ParticleTypes.WITCH, world, p);
                    ParticleUtils.spawn(ParticleTypes.SNEEZE, world, p);

                }
            }
        }
    }

}
