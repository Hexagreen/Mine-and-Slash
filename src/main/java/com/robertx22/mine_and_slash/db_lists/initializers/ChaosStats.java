package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.chaos_stats.ChaosStat;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.types.traits.atronachs.EarthAtronach;
import com.robertx22.mine_and_slash.database.stats.types.traits.atronachs.FireAtronach;
import com.robertx22.mine_and_slash.database.stats.types.traits.atronachs.FrostAtronach;
import com.robertx22.mine_and_slash.database.stats.types.traits.atronachs.ThunderAtronach;
import com.robertx22.mine_and_slash.database.stats.types.traits.bad_and_good.Barbarian;
import com.robertx22.mine_and_slash.database.stats.types.traits.bad_and_good.ClumsyScholar;
import com.robertx22.mine_and_slash.database.stats.types.traits.bad_ones.*;
import com.robertx22.mine_and_slash.database.stats.types.traits.cause_stats.OnCritBuffSpeed;
import com.robertx22.mine_and_slash.database.stats.types.traits.cause_stats.OnDodgeBuffSpeed;
import com.robertx22.mine_and_slash.database.stats.types.traits.ele_lords.LordOfBlizzardsTrait;
import com.robertx22.mine_and_slash.database.stats.types.traits.ele_lords.LordOfEarthquakesTrait;
import com.robertx22.mine_and_slash.database.stats.types.traits.ele_lords.LordOfThunderstormsTrait;
import com.robertx22.mine_and_slash.database.stats.types.traits.ele_lords.LordOfVolcanoesTrait;
import com.robertx22.mine_and_slash.database.stats.types.traits.good.*;
import com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana.*;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class ChaosStats implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        Requirements majorArcanaReq = new Requirements(SlotRequirement.armorsOnly());
        Requirements anyExceptWeapon = new Requirements(SlotRequirement.allExceptWeapon());
        Requirements weaponOnly = new Requirements(SlotRequirement.weaponsOnly());
        Requirements jewerlyOnly = new Requirements(SlotRequirement.jewerlyOnly());
        Requirements armorOnly = new Requirements(SlotRequirement.armorsOnly());

        List<ChaosStat> all = new ArrayList<>();

        all.add(new ChaosStat(anyExceptWeapon, new EarthAtronach())); // good
        all.add(new ChaosStat(anyExceptWeapon, new FireAtronach())); // good
        all.add(new ChaosStat(anyExceptWeapon, new FrostAtronach())); // good
        all.add(new ChaosStat(anyExceptWeapon, new ThunderAtronach())); // good
        all.add(new ChaosStat(anyExceptWeapon, new Barbarian()));
        all.add(new ChaosStat(anyExceptWeapon, new ClumsyScholar()));
        all.add(new ChaosStat(anyExceptWeapon, new Crippled()));
        all.add(new ChaosStat(anyExceptWeapon, new Diseased()));
        all.add(new ChaosStat(anyExceptWeapon, new OnCritBuffSpeed())); // good
        all.add(new ChaosStat(anyExceptWeapon, new OnDodgeBuffSpeed())); // good
        all.add(new ChaosStat(anyExceptWeapon, new LordOfBlizzardsTrait())); // good // Holy
        all.add(new ChaosStat(anyExceptWeapon, new LordOfEarthquakesTrait())); // good // Bulwark
        all.add(new ChaosStat(anyExceptWeapon, new LordOfThunderstormsTrait())); // good // Summoner
        all.add(new ChaosStat(anyExceptWeapon, new Armored())); // good
        all.add(new ChaosStat(anyExceptWeapon, new Elemental())); // good
        all.add(new ChaosStat(anyExceptWeapon, new Golem())); // good
        all.add(new ChaosStat(anyExceptWeapon, new Stealthy())); // good

        all.add(new ChaosStat(weaponOnly, new Lucky())); // good
        all.add(new ChaosStat(weaponOnly, new Clumsy()));
        all.add(new ChaosStat(weaponOnly, new Cursed()));
        all.add(new ChaosStat(weaponOnly, new WeaponMaster())); // good
        all.add(new ChaosStat(weaponOnly, new LordOfVolcanoesTrait())); // good // Seething

        all.add(new ChaosStat(jewerlyOnly, new Clueless()));

        all.add(new ChaosStat(armorOnly, new Devoted()));

        all.add(new ChaosStat(new Requirements(SlotRequirement.allExceptWeapon(), LevelRequirement.levelingProcess()),
            new QuickLearner()));

        all.add(new ChaosStat(majorArcanaReq, new TheMagician()));
        all.add(new ChaosStat(majorArcanaReq, new Chariot()));
        all.add(new ChaosStat(majorArcanaReq, new Death()));
        all.add(new ChaosStat(majorArcanaReq, new HangedMan()));
        all.add(new ChaosStat(majorArcanaReq, new Hermit()));
        all.add(new ChaosStat(majorArcanaReq, new HighPriestess()));
        all.add(new ChaosStat(majorArcanaReq, new Judgement()));
        all.add(new ChaosStat(majorArcanaReq, new Justice()));
        all.add(new ChaosStat(majorArcanaReq, new StrengthArcana()));
        all.add(new ChaosStat(majorArcanaReq, new Temperance()));
        all.add(new ChaosStat(majorArcanaReq, new TheDevil()));
        all.add(new ChaosStat(majorArcanaReq, new TheEmperor()));
        all.add(new ChaosStat(majorArcanaReq, new TheEmpress()));
        all.add(new ChaosStat(majorArcanaReq, new TheFool()));
        all.add(new ChaosStat(majorArcanaReq, new TheHierophant()));
        all.add(new ChaosStat(majorArcanaReq, new TheMoon()));
        all.add(new ChaosStat(majorArcanaReq, new TheLovers()));
        all.add(new ChaosStat(majorArcanaReq, new TheStar()));
        all.add(new ChaosStat(majorArcanaReq, new TheSun()));
        all.add(new ChaosStat(majorArcanaReq, new TheWorld()));
        all.add(new ChaosStat(majorArcanaReq, new Tower()));
        all.add(new ChaosStat(majorArcanaReq, new WheelOfFortune()));

        all.forEach(x -> x.registerToSlashRegistry());

    }
}
