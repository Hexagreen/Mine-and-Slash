package com.robertx22.mine_and_slash.database.world_providers.base;

import net.minecraft.world.GameRules;

public class MyGameRules extends GameRules {

    public MyGameRules(GameRules rules) {
        this.rules = rules;
    }

    GameRules rules;

    @Override
    public boolean getBoolean(GameRules.RuleKey<GameRules.BooleanValue> val) {
        if (val == GameRules.DO_FIRE_TICK || val == GameRules.DO_MOB_SPAWNING) {
            return false;
        } else {
            return rules.getBoolean(val);
        }
    }
}
