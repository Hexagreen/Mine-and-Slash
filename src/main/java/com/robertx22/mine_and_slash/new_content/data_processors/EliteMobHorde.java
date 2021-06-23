package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.new_content.data_processors.bases.ChunkProcessData;
import com.robertx22.mine_and_slash.new_content.data_processors.bases.SpawnedMob;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.MobSpawnUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class EliteMobHorde extends DataProcessor {

    public EliteMobHorde() {
        super("elite_mob_horde");
    }

    @Override
    public void processImplementation(String key, BlockPos pos, IWorld world, ChunkProcessData data) {

        EntityType<? extends MobEntity> type = SpawnedMob.random(data.getRoom()).type;

        int amount = RandomUtils.RandomRange(2, 4); // add variability

        for (int i = 0; i < amount; i++) {
            MobSpawnUtils.summonElite(type, world, pos);
        }
    }
}