package com.robertx22.uncommon.utilityclasses;

import com.robertx22.database.stats.types.resources.Health;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.EntityLivingBase;

public class HealthUtils {

	public static float DamageToMinecraftHealth(float dmg, EntityLivingBase entity) {

		try {
			Unit unit = UnitSaving.Load(entity);

			float maxhp = unit.Stats.get(new Health().Name()).Value;
			float maxMChp = entity.getMaxHealth();

			return (float) (maxMChp / maxhp * dmg);
		} catch (Exception e) {
		}
		return 0;

	}

	/*
	 * public static float UnitHPtoMcHP(EntityLivingBase entity) {
	 * 
	 * try { Unit unit = UnitSaving.Load(entity);
	 * 
	 * float currentHP = unit.health().GetCurrentValue(); float maxMcHP =
	 * entity.getMaxHealth();
	 * 
	 * return (float) maxMcHP * currentHP / unit.health().Value; } catch (Exception
	 * e) { } return 0;
	 * 
	 * }
	 */

}
