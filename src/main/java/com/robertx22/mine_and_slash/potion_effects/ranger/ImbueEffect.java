package com.robertx22.mine_and_slash.potion_effects.ranger;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.ImbueSpell;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Spells;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ImbueEffect extends BasePotionEffect {

    private ImbueEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    public static ImbueEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "imbue";
    }

    @Override
    public String locNameForLangFile() {
        return "Imbue";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        p.set(SC.BASE_VALUE, 2, 5);
        p.set(SC.ATTACK_SCALE_VALUE, 0.18F, 0.36F);
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return ImbueSpell.getInstance();
    }

    @Override
    public Masteries getMastery() {
        return Masteries.HUNTING;
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.GRAY+ "" + TextFormatting.ITALIC + Spells.ScalePhyAtk.getLocNameStr()));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + Words.Wep2Phy.locName().getString()));
        TooltipUtils.addEmpty(list);
		list.addAll(descLocName("", TextFormatting.GREEN));

        list.addAll(getCalc(info.player)
            .GetTooltipString(info, Load.spells(info.player), this));

        return list;

    }

    private static class SingletonHolder {
        private static final ImbueEffect INSTANCE = new ImbueEffect();
    }
}

