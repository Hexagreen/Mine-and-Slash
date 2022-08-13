package com.robertx22.mine_and_slash.uncommon.localization;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;

import java.util.Iterator;
import java.util.List;
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
    SpellAbility("Ability "),
    SpellTotalMastery("Total Mastery "),

    // bases.SpellPredicates.java
    Need_Ranged("Requires Ranged Weapon"),
    Need_Melee("Requires Melee Weapon"),

    // SpellTooltips.java
    Single_Target_Proj("Throw a projectile, damaging first enemy hit: "),
    Self_Buff("Applies buff to caster: "),

    // General Spell Tooltip
    AttackSpell("Attack Spell"),
    AttackSpellDesc("Spell that also triggers on-attack effects."),
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

    // gui.screens.spell_schools.SpellSchoolScreen.java
    UnlockSecondMastery("You can unlock a second Mastery tree at level "),
    UnlockThirdMastery("You can unlock a third Mastery tree at level "),
    MasteryLevelCap("Your total Mastery level cannot go past your max level."),


    ;

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


}
