package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.lists.GearTypes;
import com.robertx22.database.lists.Rarities;
import com.robertx22.gearitem.GearItemSlot;
import com.robertx22.gearitem.IStatsContainer;
import com.robertx22.gearitem.ITooltip;
import com.robertx22.gearitem.ITooltipList;
import com.robertx22.gearitem.ItemRarity;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class GearItemData implements IStatsContainer, Serializable, ITooltip {

	private static final long serialVersionUID = -8327205425334275976L;

	public int Rarity;
	public String gearTypeName;
	public String name = "Error no name";
	public int level;

	public PrimaryStatsData primaryStats;
	public SecondaryStatsData secondaryStats;

	public SuffixData suffix;
	public PrefixData prefix;

	public GearItemSlot GetBaseGearType() {

		return GearTypes.All.get(gearTypeName);
	}

	public ItemRarity GetRarity() {
		return Rarities.Items.get(Rarity);
	}

	public String GetDisplayName() {

		String text = GetRarity().Color();

		if (prefix != null) {
			text += prefix.BaseAffix().Name() + " ";
		}
		text += name;

		if (suffix != null) {
			text += suffix.BaseAffix().Name() + " ";
		}

		return text;

	}

	@Override
	public List<StatModData> GetAllStats() {

		List<StatModData> datas = new ArrayList<StatModData>();

		if (suffix != null) {
			datas.addAll(suffix.GetAllStats());
		}
		if (prefix != null) {
			datas.addAll(prefix.GetAllStats());
		}

		if (primaryStats != null) {
			datas.addAll(primaryStats.GetAllStats());
		}
		if (secondaryStats != null) {
			datas.addAll(secondaryStats.GetAllStats());
		}

		return datas;
	}

	@Override
	public void BuildTooltip(ItemTooltipEvent event) {

		event.getToolTip().clear();

		event.getToolTip().add(GetDisplayName());
		event.getToolTip().add(TextFormatting.YELLOW + "Level: " + level);

		event.getToolTip().add("");

		List<ITooltipList> list = new ArrayList<ITooltipList>();
		list.add(primaryStats);
		list.add(secondaryStats);
		list.add(prefix);
		list.add(suffix);

		for (ITooltipList part : list) {

			if (part != null) {
				event.getToolTip().addAll(part.GetTooltipString());
				event.getToolTip().add("");

			}

		}

	}

}
