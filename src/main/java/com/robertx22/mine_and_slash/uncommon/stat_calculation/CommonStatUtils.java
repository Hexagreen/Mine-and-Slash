package com.robertx22.mine_and_slash.uncommon.stat_calculation;

import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.ICoreStat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.IPreCoreStat;
import com.robertx22.mine_and_slash.db_lists.initializers.Stats;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.mapitem.MapAffixData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.capability.world.WorldMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatConversion;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatTransfer;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

import java.util.List;

public class CommonStatUtils {

    public static void addCustomStats(UnitData data, Unit unit, int level) {
        for (StatModData stat : data.getCustomStats().stats.values()) {
            stat.applyStats(data, level);
        }
    }

    public static void addExactCustomStats(UnitData data) {
        for (ExactStatData stat : data.getCustomExactStats().stats.values()) {
            stat.applyStats(data);
        }
    }

    public static void addPotionStats(LivingEntity entity, UnitData data) {

        PlayerSpellCap.ISpellsCap cap = Load.spells(entity);

        for (EffectInstance instance : entity.getActivePotionEffects()) {
            if (instance.getPotion() instanceof IApplyStatPotion) {
                IApplyStatPotion stat = (IApplyStatPotion) instance.getPotion();
                try {
                    stat.applyStats(data, cap, instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void CalcCoreStats(UnitData unit) {

        Unit theunit = unit.getUnit();

        for (IPreCoreStat core : Stats.allPreGenMapStatLists.get(IPreCoreStat.class)) {

            StatData statdata = theunit.peekAtStat(core.GUID());
            if (statdata.isMoreThanZero()) {
                core.addToCoreStats(unit, statdata);
            }

        }
        for (ICoreStat core : Stats.allPreGenMapStatLists.get(ICoreStat.class)) {

            StatData statdata = theunit.peekAtStat(core.GUID());
            if (statdata.isMoreThanZero()) {
                core.addToOtherStats(unit, statdata);
            }

        }
    }

    public static void CalcTraits(UnitData unit) {

        Unit theunit = unit.getUnit();

        for (Trait trait : Stats.allPreGenMapStatLists.get(Trait.class)) {

            StatData statdata = theunit.peekAtStat(trait.GUID());
            if (statdata.isMoreThanZero()) {
                trait.TryAffectOtherStats(unit, statdata);
            }

        }
        for (IAffectsStats trait : Stats.allPreGenMapStatLists.get(IAffectsStats.class)) {

            StatData statdata = theunit.peekAtStat(trait.GUID());
            if (statdata.isMoreThanZero()) {
                trait.affectStats(unit, statdata);
            }

        }
    }

    /**
     * A unit copy is needed so there's no randomness to stat transfers and
     * conversions. All changes are based on old copy but applied to the unit that's
     * used
     */
    public static void CalcStatConversionsAndTransfers(Unit copy, Unit unit) {

        for (IStatConversion core : Stats.allPreGenMapStatLists.get(IStatConversion.class)) {

            StatData statdata = copy.peekAtStat(core.GUID());
            if (statdata.isMoreThanZero()) {
                core.convertStats(copy, unit, copy.getCreateStat(core.GUID()));
            }

        }
        for (IStatTransfer core : Stats.allPreGenMapStatLists.get(IStatTransfer.class)) {

            StatData statdata = copy.peekAtStat(core.GUID());
            if (statdata.isMoreThanZero()) {
                core.transferStats(copy, unit, copy.getCreateStat(core.GUID()));
            }

        }

    }

    public static void AddMapAffixStats(WorldMapCap.IWorldMapData mapdata, Unit unit, int level, LivingEntity entity) {

        for (MapAffixData status : WorldUtils.getAllAffixesThatAffect(mapdata, entity)) {
            List<StatModData> datas = status.GetAllStats();
            for (StatModData statModData : datas) {
                StatData statData = unit.getCreateStat(statModData.getStatMod()
                    .GetBaseStat());
                if (statData == null) {
                } else {

                    statData.add(statModData, level);

                }
            }
        }

    }

}
