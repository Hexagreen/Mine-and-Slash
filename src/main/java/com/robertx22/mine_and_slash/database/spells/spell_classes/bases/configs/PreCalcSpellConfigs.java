package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

import com.google.common.base.Preconditions;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellStatsCalcEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// this class should be easy to serialize as a config
// synergies add to these values.
public class PreCalcSpellConfigs {

    public PreCalcSpellConfigs() {
        set(SC.TIMES_TO_CAST, 1, 1);
        set(SC.COOLDOWN_TICKS, 0, 0);
        set(SC.COOLDOWN_SECONDS, 0, 0);
    }

    private PreCalcSpellConfigs(boolean nothing) {

    }

    public static PreCalcSpellConfigs getEmptyForSynergies() {
        return new PreCalcSpellConfigs(false);
    }

    private HashMap<SC, LevelBased> map = new HashMap<>();

    public int maxSpellLevel = 12;

    public SpellCalcData getCalc(PlayerSpellCap.ISpellsCap cap, IAbility ability) {
        if (has(SC.ATTACK_SCALE_VALUE)) {
            return SpellCalcData.scaleWithAttack(
                get(SC.ATTACK_SCALE_VALUE).get(cap, ability),
                get(SC.BASE_VALUE).get(cap, ability)
            );
        }
        else if (has(SC.ELEMENTAL_ATTACK_SCALE_VALUE)){
            return SpellCalcData.scaleWithElementalAttack(
                    get(SC.ELEMENTAL_ATTACK_SCALE_VALUE).get(cap, ability),
                    get(SC.BASE_VALUE).get(cap, ability)
            );
        }
        else if (has(SC.PHYSICAL_ATTACK_SCALE_VALUE)){
            return SpellCalcData.scaleWithPhysicalAttack(
                    get(SC.PHYSICAL_ATTACK_SCALE_VALUE).get(cap, ability),
                    get(SC.BASE_VALUE).get(cap, ability)
            );
        }
        else if (has(SC.FIRE_ATTACK_SCALE_VALUE)){
            return SpellCalcData.scaleWithFireAttack(
                    get(SC.FIRE_ATTACK_SCALE_VALUE).get(cap, ability),
                    get(SC.BASE_VALUE).get(cap, ability)
            );
        }
        else if (has(SC.WATER_ATTACK_SCALE_VALUE)){
            return SpellCalcData.scaleWithWaterAttack(
                    get(SC.WATER_ATTACK_SCALE_VALUE).get(cap, ability),
                    get(SC.BASE_VALUE).get(cap, ability)
            );
        }
        else if (has(SC.THUNDER_ATTACK_SCALE_VALUE)){
            return SpellCalcData.scaleWithThunderAttack(
                    get(SC.THUNDER_ATTACK_SCALE_VALUE).get(cap, ability),
                    get(SC.BASE_VALUE).get(cap, ability)
            );
        }
        else if (has(SC.NATURE_ATTACK_SCALE_VALUE)){
            return SpellCalcData.scaleWithNatureAttack(
                    get(SC.NATURE_ATTACK_SCALE_VALUE).get(cap, ability),
                    get(SC.BASE_VALUE).get(cap, ability)
            );
        }
        else if (has(SC.ARMOR_ATTACK_SCALE_VALUE)){
            return SpellCalcData.scaleWithArmorAttack(
                    get(SC.ARMOR_ATTACK_SCALE_VALUE).get(cap, ability),
                    get(SC.BASE_VALUE).get(cap, ability)
            );
        }
        else if (has(SC.HEALTH_ATTACK_SCALE_VALUE)){
            return SpellCalcData.scaleWithHealthAttack(
                    get(SC.HEALTH_ATTACK_SCALE_VALUE).get(cap, ability),
                    get(SC.BASE_VALUE).get(cap, ability)
            );
        }
        else if (has(SC.ENERGY_ATTACK_SCALE_VALUE)){
            return SpellCalcData.scaleWithEnergyAttack(
                    get(SC.ENERGY_ATTACK_SCALE_VALUE).get(cap, ability),
                    get(SC.BASE_VALUE).get(cap, ability)
            );
        }
        else if (has(SC.MANA_ATTACK_SCALE_VALUE)){
            return SpellCalcData.scaleWithManaAttack(
                    get(SC.MANA_ATTACK_SCALE_VALUE).get(cap, ability),
                    get(SC.BASE_VALUE).get(cap, ability)
            );
        }
        else {
            return SpellCalcData.base(
                    get(SC.BASE_VALUE).get(cap, ability)
            );
        }
    }

    public boolean has(SC sc) {
        return map.containsKey(sc);
    }

    public HashMap<SC, LevelBased> getMap() {
        return map;
    }

    public void set(SC sc, float min, float max) {

        map.put(sc, new LevelBased(min, max).min(sc.min));
    }

    public void setDurationInSeconds(int s1, int s2) {
        set(SC.DURATION_TICKS, s1 * 20, s2 * 20);
    }

    public void multiplyValueBy(SC sc, float multi) {
        get(sc).multiplyBy(multi);
    }

    public void setMaxLevel(int lvl) {
        this.maxSpellLevel = lvl;
    }

    public LevelBased getOrEmpty(SC sc) {
        if (has(sc)) {
            return get(sc);
        }
        return LevelBased.empty();
    }

    public LevelBased get(SC sc) {

        if (!map.containsKey(sc)) {

            try {
                throw new Exception("Trying to get non existent value: " + sc.name());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return LevelBased.empty();
        }

        LevelBased thing = map.get(sc);

        if (thing.isEmpty()) {
            try {
                throw new Exception("Getting empty value: " + sc.name());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return LevelBased.empty();
        }

        return thing;

    }

    private boolean modifiedBySynergies = false;

    public void modifyByUserStats(SpellCastContext ctx) {
        new SpellStatsCalcEffect(ctx, this, ctx.caster, ctx.caster).Activate();
    }

    public void modifyBySynergies(BaseSpell spell, PlayerSpellCap.ISpellsCap cap) {

        Preconditions.checkArgument(
            modifiedBySynergies == false,
            "Cannot modify same spell calc config instance twice with synergies!,Make sure you're returning new config instances on each method call!!!");

        spell.getAllocatedSynergies(cap)
            .forEach(x -> {
                PreCalcSpellConfigs sc = x.getConfigsAffectingSpell();

                sc.map.entrySet()
                    .forEach(e -> {
                        this.map.get(e.getKey())
                            .modifyBy(e.getValue());
                    });

            });

        this.modifiedBySynergies = true;

    }

    public List<ITextComponent> GetTooltipString(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        if (Screen.hasShiftDown()) {
            map.entrySet()
                .forEach(x -> {
                    if (x.getKey()
                        .shouldAddToTooltip()) {
                        String val = NumberUtils.trimFloat(x.getValue()
                            .get(ctx.spellsCap, ctx.ability));
                        list.add(new SText(TextFormatting.GRAY + "").appendSibling(x.getKey().word.locName())
                            .appendText(": " + TextFormatting.GREEN + val));

                    }
                });
        }
        return list;

    }
}
