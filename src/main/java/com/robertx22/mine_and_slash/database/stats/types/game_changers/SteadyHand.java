package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.effects.game_changers.SteadyHandEffect;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.offense.IncreaseDamage;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class SteadyHand extends BaseGameChangerTrait {

    private SteadyHand() {
    }

    public static final SteadyHand INSTANCE = new SteadyHand();

    @Override
    public String locDescForLangFile() {
        return "Deal more damage at the expense of critical hits.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/steady_hand";
    }

    @Override
    public String locNameForLangFile() {
        return "Steady Hand";
    }

    @Override
    public String GUID() {
        return "steady_hand_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
                new ExactStatData(25, StatModTypes.Flat, IncreaseDamage.getInstance()),
            new ExactStatData(-1000, StatModTypes.Multi, CriticalHit.getInstance()),
            new ExactStatData(-1000, StatModTypes.Multi, CriticalDamage.getInstance())
        );
    }

}
