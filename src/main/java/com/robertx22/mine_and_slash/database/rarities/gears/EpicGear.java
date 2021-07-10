package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseEpic;

public class EpicGear extends BaseEpic implements GearRarity {
    public EpicGear() {
    }

    public static EpicGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float requirementMulti() {
        return 0.8F;
    }

    @Override
    public float unidentifiedChance() {
        return 90;
    }

    @Override
    public MinMax secondaryStatPercents() {
        return StatPercents();
    }

    @Override
    public MinMax affixStatPercents() {
        return StatPercents();
    }

    @Override
    public MinMax uniqueStatPercents() {
        return StatPercents();
    }

    @Override
    public MinMax primaryStatPercents() {
        return StatPercents();
    }

    @Override
    public int AffixChance() {
        return 80;
    }

    @Override
    public MinMax secondaryStatAmount() {
        return new MinMax(2, 2);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(45, 90);
    }

    @Override
    public int SetChance() {
        return 50;
    }

    @Override
    public float salvageLotteryWinChance() {
        return 10;
    }

    @Override
    public int Weight() {
        return 375;
    }

    @Override
    public int runeSlots() {
        return 4;
    }

    @Override
    public float itemTierPower() {
        return 2.5F;
    }

    private static class SingletonHolder {
        private static final EpicGear INSTANCE = new EpicGear();
    }
}
