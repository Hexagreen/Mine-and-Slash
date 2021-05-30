package com.robertx22.mine_and_slash.database.spells.entities.bases;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public abstract class BaseCloudEntity extends BaseInvisibleEntity {

    public BaseCloudEntity(EntityType type, World world) {
        super(type, world);
    }

    @Override
    public void initSpellEntity() {

    }

    public abstract void onHit(LivingEntity entity);

    public abstract void summonFallParticle(Vec3d p);

    public boolean spawnCloudParticles() {
        return true;
    }

    @Override
    public void onTick() {
        try {
            EntitySpellData sdata = getSpellData();
            int TICK_RATE = sdata.configs.get(SC.TICK_RATE)
                .intValue();
            float RADIUS = sdata.configs.get(SC.RADIUS);

            if (this.ticksExisted % TICK_RATE == 1) {

                if (!this.world.isRemote) {

                    List<LivingEntity> entities = EntityFinder.start(
                        getCaster(), LivingEntity.class, getPositionVector())
                        .radius(RADIUS)
                        .build();

                    entities.forEach(x -> onHit(x));

                }
            }

            if (world.isRemote && ticksExisted % 2 == 0) {

                for (int i = 1; i < 12; i++) {
                    double speed = (rand.nextBoolean() ? 1 : -1) * 0.1 + 0.05 * rand.nextDouble();

                    float yRandom = (float) RandomUtils.RandomRange(1, 100) / 40F;

                    float height = 4;

                    Vec3d p = GeometryUtils.getRandomHorizontalPosInRadiusCircle(
                        posX, posY + height + yRandom, posZ, RADIUS);

                    if (spawnCloudParticles()) {
                        for (int a = 1; a < 2; a++) {
                            ParticleUtils.spawn(ParticleTypes.CLOUD, world, p.add(0, 1, 0));
                        }
                    }

                    summonFallParticle(p);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}