package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnSpellCastSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.base.Synergy;
import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.NoEnergyPacket;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.SpellType;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseSpell implements ISlashRegistryEntry<BaseSpell>, ITooltipList, IAbility {

    private final ImmutableSpellConfigs immutableConfigs;

    public BaseSpell(ImmutableSpellConfigs immutable) {
        this.immutableConfigs = immutable;
    }

    public final ImmutableSpellConfigs getImmutableConfigs() {
        return immutableConfigs;
    }

    public final void onCastingTick(SpellCastContext ctx) {

        int timesToCast = (int) ctx.getConfigFor(this)
            .get(SC.TIMES_TO_CAST)
            .get(ctx.spellsCap, this);

        if (timesToCast > 1) {

            int castTimeTicks = (int) ctx.getConfigFor(this)
                .get(SC.CAST_TIME_TICKS)
                .get(ctx.spellsCap, this);

            // if i didnt do this then cast time reduction would reduce amount of spell hits.
            int castEveryXTicks = castTimeTicks / timesToCast;

            if (ctx.ticksInUse > 0 && ctx.ticksInUse % castEveryXTicks == 0) {
                this.cast(ctx);
            }

            /*
            if (ctx.isLastCastTick) {
                this.cast(ctx);
            } else {
                if (ctx.ticksInUse > 0 && ctx.ticksInUse % castEveryXTicks == 0) {
                    this.cast(ctx);
                }
            }*/

        } else if (timesToCast < 1) {
            System.out.println("Times to cast spell is: " + timesToCast + " . this seems like a bug.");
        }

    }

    @Override
    public ITextComponent getLocName() {
        return this.getName()
            .locName();
    }

    public void spawnParticles(SpellCastContext ctx) {

    }

    public final List<Synergy> getAllocatedSynergies(PlayerSpellCap.ISpellsCap cap) {
        return cap.getAbilitiesData()
            .getAllocatedSynergies()
            .stream()
            .filter(x -> x.getRequiredAbility()
                .GUID()
                .equals(this.GUID()))
            .collect(Collectors.toList());
    }

    @Override
    public BaseSpell getSpell() {
        return this;
    }

    public final int getMaxSpellLevelNormal() {
        return getPreCalcConfig().maxSpellLevel;
    }

    public final int getMaxSpellLevelBuffed() {
        return getMaxSpellLevelNormal() * 2;
    }

    public boolean shouldActivateCooldown(PlayerEntity player, PlayerSpellCap.ISpellsCap spells) {
        return true;
    }

    public enum AllowedAsRightClickOn {
        MAGE_WEAPON, MELEE_WEAPON, NONE
    }

    @Override
    public Type getAbilityType() {
        return Type.SPELL;
    }

    @Override
    public IAbility getRequiredAbility() {
        return null;
    }

    public boolean isAllowedAsRightClickFor(GearItemSlot slot) {
        switch (immutableConfigs.allowedAsRightClickOn()) {
            case NONE: {
                return false;
            }
            case MELEE_WEAPON: {
                return slot.isMeleeWeapon() || slot.isMageWeapon();
            }
            case MAGE_WEAPON: {
                return slot.isMageWeapon();
            }
        }
        return false;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

    public final boolean goesOnCooldownIfCastCanceled() {
        // override for spells that do oncastingtick
        return immutableConfigs.goesOnCooldownIfCanceled();
    }

    @Override
    public final ResourceLocation getIconLoc() {
        return new ResourceLocation(Ref.MODID, "textures/gui/spells/" + getMastery().id + "/" + GUID() + ".png");
    }

    @Override
    public final Masteries getMastery() {
        return immutableConfigs.school();
    }

    public int getCooldownInTicks(SpellCastContext ctx) {
        int ticks = (int) ctx.getConfigFor(this)
            .get(SC.COOLDOWN_TICKS)
            .get(ctx.spellsCap, this);

        int seconds = (int) ctx.getConfigFor(this)
            .get(SC.COOLDOWN_SECONDS)
            .get(ctx.spellsCap, this);

        return (int) ((seconds * 20F) + ticks);
    }

    public final int getCooldownInSeconds(SpellCastContext ctx) {
        return getCooldownInTicks(ctx) / 20;
    }

    public final int getChance(SpellCastContext ctx) {
        return (int) ctx.getConfigFor(this)
                .get(SC.CHANCE)
                .get(ctx.spellsCap, this);
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SPELL;
    }

    public abstract String GUID();

    public final float getHealthCost(SpellCastContext ctx) {
        return ctx.getConfigFor(this)
                .get(SC.HEALTH_COST)
                .get(ctx.spellsCap, this);
    }

    public final float getMagicShieldCost(SpellCastContext ctx) {
        return ctx.getConfigFor(this)
                .get(SC.MAGIC_SHIELD_COST)
                .get(ctx.spellsCap, this);
    }

    public final float getCalculatedHealthCost(SpellCastContext ctx) {
        return ctx.getConfigFor(this)
                .get(SC.HEALTH_COST)
                .get(ctx.spellsCap, this) * ctx.data.getUnit().healthData().getAverageValue();
    }

    public final int getCalculatedManaCost(SpellCastContext ctx) {
        return (int) Mana.getInstance()
            .calculateScalingStatGrowth((int) ctx.getConfigFor(this)
                .get(SC.MANA_COST)
                .get(ctx.spellsCap, this), getEffectiveAbilityLevel(ctx.spellsCap, ctx.data));
    }

    public final float getCalculatedMagicShieldCost(SpellCastContext ctx) {
        return ctx.getConfigFor(this)
                .get(SC.MAGIC_SHIELD_COST)
                .get(ctx.spellsCap, this) * ctx.data.getUnit().magicShieldData().getAverageValue();
    }

    public final int getCalculatedEnergyCost(SpellCastContext ctx) {
        return (int) Energy.getInstance()
                .calculateScalingStatGrowth((int) ctx.getConfigFor(this)
                        .get(SC.ENERGY_COST)
                        .get(ctx.spellsCap, this), getEffectiveAbilityLevel(ctx.spellsCap, ctx.data));
    }

    public final int useTimeTicks(SpellCastContext ctx) {
        return (int) ctx.getConfigFor(this)
            .get(SC.CAST_TIME_TICKS)
            .get(ctx.spellsCap, this);
    }

    public final float getUseDurationInSeconds(SpellCastContext ctx) {
        return (float) useTimeTicks(ctx) / 20;
    }

    public final SpellCalcData getCalculation(SpellCastContext ctx) {
        return ctx.getConfigFor(this)
            .getCalc(ctx.spellsCap, this);
    }

    public final Elements getElement() {
        return this.immutableConfigs.element();
    }

    public abstract List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx);

    public abstract Words getName();

    public final boolean cast(SpellCastContext ctx) {
        boolean bool = immutableConfigs.castType()
            .cast(ctx);

        getAllocatedSynergies(ctx.spellsCap).forEach(x -> {
            if (x instanceof OnSpellCastSynergy) {
                OnSpellCastSynergy s = (OnSpellCastSynergy) x;
                s.tryActivate(ctx);
            }
        });

        ctx.castedThisTick = true;

        if (getImmutableConfigs().getSwingsArmOnCast()) {
            ctx.caster.func_226292_a_(Hand.MAIN_HAND, true);
        }

        castExtra(ctx);
        return bool;
    }

    public void castExtra(SpellCastContext ctx) {

    }

    public void spendResources(SpellCastContext ctx) {
        if (getCalculatedHealthCost(ctx) > 0) {
            ctx.data.getResources()
                    .modify(getHealthCostCtx(ctx));
        }
        if (getCalculatedManaCost(ctx) > 0) {
            ctx.data.getResources()
                    .modify(getManaCostCtx(ctx));
        }
        if (getCalculatedEnergyCost(ctx) > 0) {
            ctx.data.getResources()
                    .modify(getEnergyCostCtx(ctx));
        }
        if (getCalculatedMagicShieldCost(ctx) > 0) {
            ctx.data.getResources()
                    .modify(getMagicShieldCostCtx(ctx));
        }
    }

    public ResourcesData.Context getManaCostCtx(SpellCastContext ctx) {

        float cost = 0;

        for (Synergy x : getAllocatedSynergies(ctx.spellsCap)) {
            if (ctx.getConfigFor(x)
                .has(SC.MANA_COST)) {
                cost += ctx.getConfigFor(x)
                    .get(SC.MANA_COST)
                    .get(ctx.spellsCap, x);
            }
        }

        cost += this.getCalculatedManaCost(ctx);

        return new ResourcesData.Context(
            ctx.data, ctx.caster, ResourcesData.Type.MANA, cost, ResourcesData.Use.SPEND);
    }

    public ResourcesData.Context getHealthCostCtx(SpellCastContext ctx) {

        float cost = 0;

        for (Synergy x : getAllocatedSynergies(ctx.spellsCap)) {
            if (ctx.getConfigFor(x)
                    .has(SC.HEALTH_COST)) {
                cost += ctx.getConfigFor(x)
                        .get(SC.HEALTH_COST)
                        .get(ctx.spellsCap, x) * ctx.data.getUnit().healthData().getAverageValue();
            }
        }

        cost += this.getCalculatedHealthCost(ctx);

        return new ResourcesData.Context(
                ctx.data, ctx.caster, ResourcesData.Type.HEALTH, cost, ResourcesData.Use.SPEND);
    }

    public ResourcesData.Context getMagicShieldCostCtx(SpellCastContext ctx) {

        float cost = 0;

        for (Synergy x : getAllocatedSynergies(ctx.spellsCap)) {
            if (ctx.getConfigFor(x)
                    .has(SC.MAGIC_SHIELD_COST)) {
                cost += ctx.getConfigFor(x)
                        .get(SC.MAGIC_SHIELD_COST)
                        .get(ctx.spellsCap, x) * ctx.data.getUnit().magicShieldData().getAverageValue();
            }
        }

        cost += this.getCalculatedMagicShieldCost(ctx);

        return new ResourcesData.Context(
                ctx.data, ctx.caster, ResourcesData.Type.MAGIC_SHIELD, cost, ResourcesData.Use.SPEND);
    }

    public ResourcesData.Context getEnergyCostCtx(SpellCastContext ctx) {

        float cost = 0;

        for (Synergy x : getAllocatedSynergies(ctx.spellsCap)) {
            if (ctx.getConfigFor(x)
                    .has(SC.ENERGY_COST)) {
                cost += ctx.getConfigFor(x)
                        .get(SC.ENERGY_COST)
                        .get(ctx.spellsCap, x);
            }
        }

        cost += this.getCalculatedEnergyCost(ctx);

        return new ResourcesData.Context(
                ctx.data, ctx.caster, ResourcesData.Type.ENERGY, cost, ResourcesData.Use.SPEND);
    }

    public boolean canCast(SpellCastContext ctx) {

        LivingEntity caster = ctx.caster;

        if (caster instanceof PlayerEntity == false) {
            return true;
        }

        PlayerEntity player = (PlayerEntity) caster;

        if (!caster.world.isRemote) {

            UnitData data = Load.Unit(caster);

            if (data != null) {

                ResourcesData.Context hctx = getHealthCostCtx(ctx);
                ResourcesData.Context rctx = getManaCostCtx(ctx);
                ResourcesData.Context ectx = getEnergyCostCtx(ctx);
                ResourcesData.Context msctx = getMagicShieldCostCtx(ctx);

                if (data.getResources()
                    .hasEnough(rctx) && data.getResources()
                        .hasEnough(ectx) && data.getResources()
                        .hasEnough(msctx) && data.getResources().hasEnough(hctx)) {

                    if (immutableConfigs.castRequirements()
                        .stream()
                        .anyMatch(x -> !x.predicate.test(player))) {
                        return false;
                    }

                    return true;
                } else {
                    if (caster instanceof ServerPlayerEntity) {
                        MMORPG.sendToClient(new NoEnergyPacket(), (ServerPlayerEntity) caster);
                    }

                }
            }
        }
        return false;

    }

    @Override
    public final List<ITextComponent> GetTooltipString(TooltipInfo info) {

        SpellCastContext ctx = new SpellCastContext(info.player, 0, this);

        UnitData data = info.unitdata;

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(getMastery().format + "" + TextFormatting.BOLD + "").appendSibling(
            getName().locName()));

        TooltipUtils.addEmpty(list);

        list.addAll(GetDescription(info, ctx));

        TooltipUtils.addEmpty(list);

        if (getCalculatedHealthCost(ctx) * 2 > 0.00) {
            list.add(new StringTextComponent(TextFormatting.RED + Spells.Health_Cost.getLocNameStr() + Math.round(getHealthCost(ctx) * 100) + "%" + " (" + Math.round(getCalculatedHealthCost(ctx)) + ")"));
        }
        if (getCalculatedMagicShieldCost(ctx) * 2 > 0.00) {
            list.add(new StringTextComponent(TextFormatting.AQUA + Spells.Magic_Shield_Cost.getLocNameStr() + Math.round(getMagicShieldCost(ctx) * 100) + "%" + " (" + Math.round(getCalculatedMagicShieldCost(ctx)) + ")"));
        }
        if (getCalculatedManaCost(ctx) > 0) {
            list.add(new StringTextComponent(TextFormatting.BLUE + Spells.Mana_Cost.getLocNameStr() + getCalculatedManaCost(ctx)));
        }
        if (getCalculatedEnergyCost(ctx) > 0) {
            list.add(new StringTextComponent(TextFormatting.YELLOW + Spells.Energy_Cost.getLocNameStr() + getCalculatedEnergyCost(ctx)));
        }
        list.add(new StringTextComponent(TextFormatting.GREEN + Spells.Cooldown.getLocNameStr() + getCooldownInSeconds(ctx) + "s"));
        list.add(new StringTextComponent(TextFormatting.GREEN + Spells.Cast_Time.getLocNameStr() + getUseDurationInSeconds(ctx) + "s"));

        TooltipUtils.addEmpty(list);

        //list.add(new StringTextComponent(getMastery().format + "").appendSibling(getMastery().getFullName()));

        //TooltipUtils.addEmpty(list);

        this.immutableConfigs.castRequirements()
            .forEach(x -> list.add(SpellType.getSpellPreText(x)));

        if (this.immutableConfigs.allowedAsRightClickOn() == AllowedAsRightClickOn.MAGE_WEAPON) {
            TooltipUtils.addEmpty(list);
            list.add(new SText(TextFormatting.GRAY + Spells.Bind_On_Wands.getLocNameStr()));
        } else if (this.immutableConfigs.allowedAsRightClickOn() == AllowedAsRightClickOn.MELEE_WEAPON) {
            TooltipUtils.addEmpty(list);
            list.add(new SText(TextFormatting.GRAY + Spells.Bind_On_Melee.getLocNameStr()));
        }
        TooltipUtils.addEmpty(list);

        finishTooltip(list, ctx, info);

        return list;

    }

    public List<ITextComponent> descLocName(String descTag, TextFormatting... style) {
        List<ITextComponent> desc = CLOC.longDesc(Ref.MODID + ".spell." + GUID() + ".desc" + descTag);

        List<ITextComponent> list = new ArrayList<>();

        for (ITextComponent iTextComponent : desc) {
            for(TextFormatting textStyle : style){
                iTextComponent.applyTextStyle(textStyle);
            }
            list.add(iTextComponent);
        }

        return list;
    }

    public List<ITextComponent> descLocName(String descTag){
        return descLocName(descTag, TextFormatting.RESET);
    }

}
