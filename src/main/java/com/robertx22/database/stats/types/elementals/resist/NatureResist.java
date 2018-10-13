package com.robertx22.database.stats.types.elementals.resist;

import com.robertx22.enums.Elements;
import com.robertx22.stats.Stat;

public class NatureResist extends Stat {
	public NatureResist() {
	}

	@Override
	public String Name() {
		return "Nature Resist";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return Elements.Nature;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}
