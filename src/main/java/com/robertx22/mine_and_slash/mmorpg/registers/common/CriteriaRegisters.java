package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.advacements.DropLvlPenaltyTrigger;
import com.robertx22.mine_and_slash.advacements.KillBossTrigger;
import com.robertx22.mine_and_slash.advacements.KillRarityMobTrigger;
import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class CriteriaRegisters {

    public static PlayerLevelTrigger PLAYER_LEVEL_TRIGGER;
    public static DropLvlPenaltyTrigger DROP_LVL_PENALTY_TRIGGER;
    public static KillRarityMobTrigger KILL_RARITY_MOB_TRIGGE;
    public static KillBossTrigger KILL_BOSS_TRIGGER;

    public static void register() {

        PLAYER_LEVEL_TRIGGER = CriteriaTriggers.register(new PlayerLevelTrigger());
        DROP_LVL_PENALTY_TRIGGER = CriteriaTriggers.register(new DropLvlPenaltyTrigger());
        KILL_RARITY_MOB_TRIGGE = CriteriaTriggers.register(new KillRarityMobTrigger());
        KILL_BOSS_TRIGGER = CriteriaTriggers.register(new KillBossTrigger());

    }

}
