package com.robertx22.mine_and_slash.database.bosses.base;

import com.robertx22.mine_and_slash.database.spells.synergies.base.Synergy;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.uncommon.capability.entity.BossCap;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class Boss implements ISlashRegistryEntry<Boss>, IApplyableStats {
    public boolean isFire = false;

    public List<Synergy> synergies = new ArrayList<>();
    public List<TickAction> tickActions = new ArrayList<>();

    public final void onTick(LivingEntity en) {

        if (en instanceof MobEntity) {
            MobEntity mob = (MobEntity) en;

            if (mob.getAttackTarget() != null) {
                tickActions.forEach(x -> x.onTick(en));
            }
        } else {
            tickActions.forEach(x -> x.onTick(en));
        }

    }

    public abstract IParticleData getParticle();

    public abstract ITextComponent getName();

    public final ITextComponent getNameFor(LivingEntity en) {
        return getName().appendText(" ")
                .appendSibling(en.getDisplayName());
    }

    public void onSpawn(LivingEntity en) {

    }

    public void onDeath(LivingEntity en) {

    }

    public abstract void onHealthTreshholdTriggered(LivingEntity en, BossData.HealthTreshhold treshhold);

    public void spawnMinion(BlockPos p, MobEntity minion, World world) {
        minion.setPosition(p.getX(), p.getY(), p.getZ());
        minion.getCapability(BossCap.Data)
                .ifPresent(x -> x.setIsBoss(false));

        minion.onInitialSpawn(
                world, world.getDifficultyForLocation(p), SpawnReason.REINFORCEMENT, null, (CompoundNBT) null);

        world.addEntity(minion);

    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.BOSS;
    }

    @Override
    public void applyStats(EntityCap.UnitData data, int level) {
        //data.getUnit().getCreateStat(Health.INSTANCE).Multi += 500;

    }

    public boolean hasSynergy(Synergy synergy) {
        return synergies.contains(synergy);
    }
}
