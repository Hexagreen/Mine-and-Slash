package com.robertx22.database.stats.types.offense.bonus;

import com.robertx22.uncommon.enumclasses.Elements;

public class BonusNatureDamage extends BaseBonusDamage {
	public static String GUID = "Bonus Nature DMG";

	public BonusNatureDamage() {

	}

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public Elements Element() {
		return Elements.Nature;
	}

}