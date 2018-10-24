package com.robertx22.database.stats.mods.flat;

import com.robertx22.database.stats.types.resources.Health;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class HealthFlat extends StatMod {

	public HealthFlat() {
	}

	@Override
	public String GUID() {
		return "HealthFlat";
	}

	@Override
	public int Min() {
		return 5;
	}

	@Override
	public int Max() {
		return 15;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new Health();
	}

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}
}
