package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityBaseProjectile;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.FrostBlastEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class FrostTotemEntity extends EntityBaseProjectile {

    public FrostTotemEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);
    }

    public FrostTotemEntity(World worldIn) {
        super(EntityRegister.FROST_TOTEM, worldIn);

    }

    public FrostTotemEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FROST_TOTEM, world);

    }

    @Override
    public double radius() {
        return getSpellData().configs.get(SC.RADIUS);
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.TOTEM_OF_UNDYING);
    }

    @Override
    public void onTick() {

        int tickRate = getSpellData().configs.get(SC.TICK_RATE)
            .intValue();

        LivingEntity caster = getCaster();

        if (this.ticksExisted % tickRate == 0) {
            if (!world.isRemote) {

                List<LivingEntity> entities = EntityFinder.start(getCaster(), LivingEntity.class, getPositionVector())
                        .radius(radius()).searchFor(EntityFinder.SearchFor.ENEMIES)
                        .build();

                if (entities.size() > 0) {

                    LivingEntity closest = entities.get(0);

                    for (LivingEntity en : entities) {
                        if (en != closest) {
                            if (this.getDistance(en) < this.getDistance(closest) && !(en instanceof ArmorStandEntity)) {
                                closest = en;
                            }
                        }
                    }

                    if (closest.isAlive()) {
                        Vec3d p = new Vec3d(posX, posY + 1F, posZ);
                        Vec3d t = new Vec3d(closest.posX, closest.posY + (closest.getEyeHeight() / 2), closest.posZ);

                        this.lookAt(EntityAnchorArgument.Type.EYES, t);

                        FrostBlastEntity en = SpellUtils.getSpellEntity(getSpellData().configs, new FrostBlastEntity(world), getSpellData().getSpell(), caster);
                        SpellUtils.setupProjectileForCasting(en, caster, 0.8F, this.rotationPitch, this.rotationYaw);

                        en.setLocationAndAngles(p.x, p.y, p.z, this.rotationPitch, this.rotationYaw);

                        //en.setMotion(new Vec3d(t.x - p.x, t.y - p.y, t.z - p.z));
                        ParticleUtils.spawn(ParticleTypes.CLOUD, world, p);
                        caster.world.addEntity(en);

                        SoundUtils.playSound(en, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1, 1.4F);
                    }
                }
            } else {

                SoundUtils.playSound(this, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, 1, 1);
                // TODO why isnt this being cast?

            }
        }
        if (this.ticksExisted % tickRate == 4) {
            if (!world.isRemote) {
                SoundUtils.playSound(this, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, 1, 1);
            }
        }

        if (this.inGround && world.isRemote) {

            for (int i = 0; i < 8; i++) {
                Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), (float) radius());
                ParticleUtils.spawn(ParticleTypes.ITEM_SNOWBALL, world, p);

            }

        }

    }

}
