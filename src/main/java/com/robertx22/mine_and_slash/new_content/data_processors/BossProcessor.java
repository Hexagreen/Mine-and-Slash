package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.new_content.data_processors.bases.ChunkProcessData;
import com.robertx22.mine_and_slash.new_content.data_processors.bases.SpawnedMob;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.MobSpawnUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class BossProcessor extends DataProcessor {

    public BossProcessor() {
        super("boss_mob");
    }

    @Override
    public void processImplementation(String key, BlockPos pos, IWorld world, ChunkProcessData data) {

        EntityType<? extends MobEntity> type = SpawnedMob.random(data.getRoom()).type;

        if (data.getRoom().group.canSpawnFireMobs) {
            MobSpawnUtils.summonBoss(type, world, pos, SlashRegistry.Bosses()
                    .random());
        } else {
            MobSpawnUtils.summonBoss(type, world, pos, SlashRegistry.Bosses()
                    .getFilterWrapped(x -> !x.isFire)
                    .random());
        }

    }
}