package com.robertx22.mine_and_slash.database.spells.blocks.holy_flower;

import com.robertx22.mine_and_slash.database.spells.blocks.base.BaseSpellTileEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModTileEntities;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class HolyFlowerTileEntity extends BaseSpellTileEntity {

    public HolyFlowerTileEntity() {
        super(ModTileEntities.HOLY_FLOWER.get());
    }

    @Override
    public void onTick() {

        EntitySpellData sdata = getSpellData();
        int TICK_RATE = sdata.configs.get(SC.TICK_RATE)
            .intValue();
        int RADIUS = sdata.configs.get(SC.RADIUS)
            .intValue();

        if (this.data.ticksExisted > durationInTicks() == false) {

            if (data.ticksExisted % TICK_RATE == 0) {

                LivingEntity caster = data.getCaster(world);
                EntityCap.UnitData data = Load.Unit(caster);

                ParticleEnum.sendToClients(
                    pos, world, new ParticlePacketData(pos, ParticleEnum.AOE).radius(RADIUS)
                        .motion(new Vec3d(0, 0, 0))
                        .type(ParticleTypes.DRIPPING_HONEY)
                        .amount((int) (45 * RADIUS)));
                ParticleEnum.sendToClients(
                        pos, world, new ParticlePacketData(pos, ParticleEnum.AOE).radius(RADIUS)
                                .motion(new Vec3d(0, 0, 0))
                                .type(ParticleTypes.HEART)
                                .amount((int) (15 * RADIUS)));

                List<LivingEntity> entities = EntityFinder.start(
                    caster, LivingEntity.class, new Vec3d(getPos()).add(0.5F, 0, 0.5F))
                    .radius(RADIUS)
                    .searchFor(EntityFinder.SearchFor.ALLIES)
                    .build();

                entities.forEach(x -> {

                    this.healTarget(x)
                        .Activate();

                    SoundUtils.playSound(x, SoundEvents.ITEM_CROP_PLANT, 1, 1);

                });

            }
        }

    }

}
