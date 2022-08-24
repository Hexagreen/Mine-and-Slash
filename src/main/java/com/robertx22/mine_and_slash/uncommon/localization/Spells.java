package com.robertx22.mine_and_slash.uncommon.localization;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;

import java.util.Locale;

public enum Spells implements IAutoLocName {
    // bases.BaseSpell.java
    Health_Cost("Health Cost: "),
    Magic_Shield_Cost("Magic Shield Cost: "),
    Mana_Cost("Mana Cost: "),
    Energy_Cost("Energy Cost: "),
    Cooldown("Cooldown: "),
    Cast_Time("Cast Time: "),
    Bind_On_Wands("Can be set as right-click on staves/wands."),
    Bind_On_Melee("Can be set as right-click on melee weapons."),

    // saveclasses.spells.calc.SpellCalcData.java
    ScaleAtk("Attack Damage"),
    ScaleEleAtk("Elemental Attack Damage"),
    ScalePhyAtk("Physical Attack Damage"),
    ScaleFireAtk("Fire Attack Damage"),
    ScaleWaterAtk("Frost Attack Damage"),
    ScaleThunderAtk("Lightning Attack Damage"),
    ScaleNatureAtk("Nature Attack Damage"),
    ScaleArmor("Armor"),
    ScaleHealth("Health"),
    ScaleEnergy("Energy"),
    ScaleMana("Mana"),
    ScaleMagicShld("Magic Shield"),
    BaseValue("Base Value: "),

    // saveclasses.spells.calc.BaseStatCalc.java
    SpellScale("Scales with "),

    // potion_effects.bases.IApplyStatPotion.java
    AffectStats("Affects Stats: "),

    // potion_effects.bases.BasePotionEffect.java
    SpellPotionDuration("Duration: "),
    SpellPotionStack("Max Stacks: "),

    // saveclasses.spells.IAbility.java
    EffAbilityLevel("Effective Ability Level: "),
    Element("Element: "),
    Needs("Needs "),
    Level(" Level: "),
    AbilityStats("Ability Stats:"),
    AffectSpell("Affects Spell: "),

    // uncommon.utilityclasses.TooltipUtils.java
    LevelReq("Lvl Req"),
    SpellAbility("Ability "),
    SpellTotalMastery("Total Mastery "),

    // bases.SpellPredicates.java
    reqRanged("Requires Ranged Weapon"),
    reqMelee("Requires Melee Weapon"),
    reqStarter("Requires Combo Starter Effect"),
    reqLinker("Requires Combo Extension Effect"),

    // SpellTooltips.java
    Single_Target_Proj("Throw a projectile, damaging first enemy hit: "),
    Self_Buff("Applies buff to caster: "),
    Target_Buff("Applies: "),

    // General Spell Tooltip
    AttackSpell("Attack Spell"),
    AttackSpellDesc("Spell that also triggers on-attack effects."),
    SummonAttack("Summon Attack"),
    SummonSpellDesc("Summons also triggers on-attack effects."),
    NormalSpell("Spell"),
    Synergy("Synergy"),
    HitApplyEffect("Hits have a chance to apply: "),
    HitApplySelfBuff("Hits have a chance to apply on self: "),

    // Spell Types
    Duration("Duration"),
    Projectile("Projectile"),
    Channel("Channel"),
    Area("Area"),
    Buff("Buff"),
    Storm("Storm"),
    Entity("Entity"),
    Debuff("Debuff"),
    Heal("Heal"),
    Pierce("Pierce"),
    Melee("Melee"),
    Self("Self"),
    Bounce("Bounce"),
    Chance("Chance"),
    Taunt("Taunt"),
    UniqueBuff("Unique Buff"),
    Craft("Craft"),
    Summon("Summon"),
    Movement("Movement"),
    Modifies("Modifies "),

    // gui.screens.spell_schools.SpellSchoolScreen.java
    UnlockSecondMastery("You can unlock a second Mastery tree at level "),
    UnlockThirdMastery("You can unlock a third Mastery tree at level "),
    MasteryLevelCap("Your total Mastery level cannot go past your max level."),

    descOnTick01("Effect occurs every"),
    descOnTick02(" ticks.");



    private String localization = "";

    Spells(String str) {
        this.localization = str;
    }

    @Override
    public String GUID() {
        return this.name()
                .toLowerCase(Locale.ROOT);
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Spells;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".spell_desc." + formattedGUID();
    }

    @Override
    public String locNameForLangFile() {
        return localization;
    }

    public String getLocNameStr() {
        return CLOC.blank(Ref.MODID + ".spell_desc." + formattedGUID()).getString();
    }
}
