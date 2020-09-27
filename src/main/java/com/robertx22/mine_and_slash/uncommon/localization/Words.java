package com.robertx22.mine_and_slash.uncommon.localization;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;

import java.util.Locale;

public enum Words implements IAutoLocName {
    Trickery("Trickery"),
    Bravery("Bravery"),
    Wizardry("Wizardry"),
    CooldownSeconds("Cooldown Seconds"), NatureBalm("Nature's Balm"),
    CooldownTicks("Cooldown Ticks"),
    CastTimeTicks("Cast Time Ticks"),
    Radius("Radius"),
    ProjectileCount("Projectile Count"),
    ShootSpeed("Shoot Speed"),
    SummonedEntities("Summoned Entities"),
    Chance("Chance"),
    CooldownReductionEfficiency("Cooldown Reduction Efficiency"),
    DurationTicks("Duration in Ticks"),
    TickRate("Tick Rate"),
    TimesToCast("Times to Cast"),
    Amount("Amount"),

    RightClickToTeleport("Use to teleport back from Adventure Map."),
    RightClickToIdentifyFirst("Use to identify first item in inventory."),
    ItemIsUnidentified("This item is currently sealed."),
    UseAnIdentifyScroll("An Identify Scroll can reveal it."),
    KillBoss("Kill Boss: "), BlinkStrike("Blink Strike"),
    Kill("Kill"),
    Magic_Missile("Magic Missiles"),
    Arcanist("Arcanist"),
    OpenAnyLootCrates("Open Any Loot Crates"),
    ThornBush("Thorn Bush"), HolyFlower("Holy Flower"),
    MagmaFlower("Magma Flower"),
    Fire("Fire"), Mastery("Mastery"),
    Ocean("Ocean"), Hunting("Hunting"), Rogue("Rogue"),
    Storm("Storm"), Divine("Divine"),
    Nature("Nature"),
    Cleric("Cleric"),
    HeartOfIce("Heart of Ice"),
    ThornArmor("Thorn Armor"),
    PoisonedWeapons("Poisoned Weapons"),
    BlazingInferno("Blazing Inferno"), SpearOfJudgement("Spear of Judgement"),
    DivineTribulation("Divine Tribulation"), PurifyingFires("Purifying Fires"),
    Fireball("Fireball"), FireBomb("Fire Bombs"),
    PoisonBall("Poison Ball"),
    Frostball("Frostball"),
    ThunderSpear("Thunder Spear"),
    ThunderDash("Thunder Dash"), TripleSlash("Triple Slash"),
    Thunderstorm("Thunderstorm"), Stealth("Stealth"),
    ArrowBarrage("Arrow Barrage"), WideShot("Wide Shot"), ArrowStorm("Arrow Storm"),
    RecoilShot("Recoil Shot"), TidalWave("Tidal Wave"), ThrowFlames("Throw Flames"),
    LightningTotem("Lightning Totem"), HealingAura("Healing Aura"),
    Volcano("Volcano"),
    Blizzard("Blizzard"),
    Whirpool("Whirpool"),
    Geyser("Geyser"),
    GorgonsGaze("Gorgon's Gaze"),
    Regenerate("Regenerate"),
    InstantHeal("Instant Heal"),
    RighteousFury("Righteous Fury"),
    DivineShield("Divine Shield"), Imbue("Imbue"),
    Spellbar("Spellbar"),
    StormCloudSpellDesc("Summons a storm cloud that deals damage over time."),
    Crate("Crate"),
    MythicCrate("Mythic Crate"),
    JewerlyCrafterCrate("Jewerly Crafter's Crate"),
    ArmorCrafterCrate("Armor Crafter's Crate"),
    WeaponcraftersCrate("Weapon Crafter's Crate"),
    Talents("Talents"),
    StatOverview("Stat Info"),
    StatPoints("Stat Points"),
    MapInfo("Map Info"),
    Compendium("Compendium"),
    KillMobsCollectRarityPoints("Kill mobs, collect rarity points"),
    KillMobs("Kill Mobs"),
    Bad("Bad"),
    Good("Good"),
    Average("Average"),
    Great("Great"),
    Amazing("Amazing"),
    CurrencyCrate("Crafter's Paradise Crate"),
    RuneCrate("Runecrafter's Crate"),
    CartographerCrate("Cartographer's Crate"),
    CommonerCrate("Commoner's Dream Crate"),
    UniqueCrate("Pharaoh Crate"),
    PressAltForStatInfo("Press Alt for Stat Desc"),
    MustBeMap("Must be an Adventure Map"),
    MustBeGear("Must be a Gear Item"),
    NotSpell("Not a Spell Item"),
    NotRune("Not a Rune Item"),
    Locked("Locked"),
    Broken("Broken"),
    Alchemy("Alchemy"),
    InstabilityLimitReached("Instability Limit Reached"),
    BreakChance("Break Chance"),
    Instability("Instability"),
    CraftingDeletesItemsInside("Crafting deletes items inside, empty beforehand!"),
    CurrentMapInfo("Current Map Info"),
    Decreased("Decreased"),
    Increased("Increased"),
    Flat("Flat"),
    PressShiftForRequirements("Press Shift for Requirements"),
    isUnique("Is Unique"),
    OnlyOneUniqueRune("Only One Unique Rune Per Gear"),
    hasMatchingRunes("Matching Runes are in gear"),
    canUpgradeInfusion("Can Upgrade Infusion"),
    noGroupAffix("No Group Map Affix"),
    Runelvlnothigherthanitemlvl("Rune lvl not higher than item lvl"),
    ItemHasRuneSlots("Item has empty rune slot"),
    NoDuplicateRunes("No duplicate runes of same type"),
    hasUniqueStats("Has Unique stats"),
    hasSet("Has Set"),
    hasPrimaryStats("Has Primary Stats"),
    hasSuffix("Has Suffix"),
    hasPrefix("Has Prefix"),
    isNotUnique("Is Not Unique"),
    isLowerThanLegendary("Is Not Legendary"),
    IsCommon("Is Common Rarity"),
    LvlLessThanMax("Lvl Less than max lvl"),
    NoChaosStats("Doesn't have Chaos Stats"),
    HasInfusion("Has Infusion Stats"),
    NoSuffix("Doesn't have suffix"),
    NoPrefix("Doesn't have prefix"),
    CanOnlyUseOnce("Can only use once"),
    CanOnlyUse10times("Can only use 10 times"),
    Doesnthaveset("Doesn't have set"),
    TierLessThanMax("Tier less than maximum"),
    AllowedOn("Allowed on"),
    NotAllowedOn("Not Allowed on: "),
    Unique_Gear("Unique Gear"),
    Normal_Gear("Normal Gear"),
    Runed_Gear("Runed Gear"),
    PicksUpItemsAuto("Automatically picks up certain items!"),
    HoldToPreventPickup("Hold in your hand to prevent item pickup."),
    BewareCreativeBagBug1("Beware, switching to Creative"),
    BewareCreativeBagBug2("while on server could clear the bag!"),
    AddRarestAffixes("Add Rarest Affixes"),
    PefectPrimaryStats("Perfect Primary Stats"),
    BetterPrimaryStats("Better Primary Stats"),
    AddSet("Add Set"),
    AddChaosStats("Add ChaosStats"),
    NeedsGearWithRunesInserted("Needs Gear with these runes inserted"),
    Requirements("Requirements"),
    RequirementsNotMet("Requirements not met"),
    Blueprint("Blueprint"),
    AddMajorArcana("Add Major Arcana Chaos Stats"),
    AlwaysMythicAffixes("Always has Mythic Affixes"),
    AlwaysChaosStats("Always has Chaos Stats"),
    AlwaysSet("Always has Set"),
    Armor("Armor"),
    Weapon("Weapon"),
    WeaponOffhand("Weapon/Offhand"),
    Jewerly("Jewerly"),
    Offhand("Offhand"),
    RunesNeeded("Runes needed"),

    Item_modifiable_in_station("Can be used inside Modify Station"),
    unlocks_runeword_combo("Unlocks RuneWord combination."),

    Press_Shift_For_Setup_Info("Press Shift For Setup Info"),

    Press_Shift_For_More_Info("Press Shift For More Info"),

    Penetration("Penetration"),

    Map_Device("Map Device"),

    Core_Stat("Core Stat"),

    Elemental_Attack_Damage("Elemental Attack Damage"),

    Main("Main"),
    Unidentified("Unidentified"),
    Activation_Time("Activation Time"),

    Affixes_Affecting_All("Affixes Affecting All"),

    Animal("Animal"),

    Attack("Attack"),

    BaseValue("Base Value"),

    Works_when_equipped("Works when equipped"),

    Blocks("Blocks"),

    Automatically_salvages_items("Automatically salvages items"),
    Gives("Gives"),
    ClickToUse("Click to Use"),
    Loot("Loot"), Exp("Exp"),

    Bonus_Salvage_Chance("Bonus Salvage Chance"),

    Bonus_Success_Rate("Bonus Success Rate"),

    By("By"),

    Chaos_Stats("Chaos Stats"),

    Cooldown("Cooldown"),

    Currency("Currency"),

    Damage("Damage"),

    Dealt("Dealt"),

    Defenses("Defenses"),

    Distance("Distance"),

    Empty("Empty"),

    From("From"),

    Fuel("Fuel"),

    Gears("Gears"),

    Infusion("Infusion"),

    Item("Item"),

    Left("Left"),

    Level("Level"),

    Major_Arcana("Major Arcana"),

    Major_Failure_Chance("Major Failure Chance"),

    Major_Success_Bonus("Major Success Bonus"),

    Major_Success_Chance("Major Success Chance"),

    Mana_Cost("Mana Cost"),

    Map("Map"),

    Maps("Maps"),

    Minutes("Minutes"),

    Misc("Misc"),

    Mob("Mob"),

    Mob_Affixes("Mob Affixes"),

    Multi("Multi"),

    None("None"),

    Not_a_Map_World("Not a Map World"),

    Npc("Npc"),

    Percent("Percent"),

    GroupPlay("Group Play"),

    PartySize("Party Size"),

    Permadeath("Permadeath"),

    Player_Affixes("Player Affixes"),

    Position("Position"),

    Prefix("Prefix"),

    Primary_Stats("Primary Stats"),

    Progress("Progress"),

    Put_Map("Put Map"),

    Rarity("Rarity"),

    Regeneration("Regenerate"),

    Resources("Resources"),

    Runed("Runed"),

    Runes("Runes"),

    Runeword("Runeword"),

    Sacrifice_Map_For_Level("Sacrifice Map For Level"),

    Sacrifice_Map_For_Tier("Sacrifice Map For Tier"),

    Scaling_Value("Scaling Value"),

    Secondary_Stats("Secondary Stats"),

    Seconds("Seconds"),

    Set("Set"),

    Socket("Socket"),

    Sockets("Sockets"),

    Spell("Spell"),

    Spell_Damage("Spell Damage"),

    Spells("Spells"),

    Start("Start"),

    Stats("Stats"),

    Suffix("Suffix"), SummonSpiders("Summon Spiders"), SpiritWolves("Spirit Wolves"),

    Tier("Tier"),

    To("To"),

    Took("Took"),

    Type("Type"),

    Unique_Stats("Unique Stats"),

    Unsalvagable("Unsalvagable"),

    UsableOn("Usable on"),

    Use_Time("Use Time"),

    Uses("Uses"),

    Beware("Beware"),

    DoNotBuildInMaps("Do not build in maps!"),

    World_Type("World Type"),

    Repair_Station("Repair Station");

    private String localization = "";

    Words(String str) {
        this.localization = str;

    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Words;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".word." + formattedGUID();
    }

    @Override
    public String locNameForLangFile() {
        return localization;
    }

    @Override
    public String GUID() {
        return this.name()
            .toLowerCase(Locale.ROOT);
    }
}
