package com.robertx22.generation;

import com.robertx22.database.lists.Rarities;
import com.robertx22.database.lists.Spells;
import com.robertx22.datasaving.SpellSaving;
import com.robertx22.gearitem.ItemRarity;
import com.robertx22.generation.blueprints.SpellBlueprint;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.utilityclasses.ListUtils;
import com.robertx22.utilityclasses.WeightedUtils;

import net.minecraft.item.ItemStack;

public class ItemSpellGen {

	public static ItemStack Random() {

		BaseSpell spell = (BaseSpell) WeightedUtils.WeightedRandom(ListUtils.CollectionToList(Spells.All.values()));
		ItemStack stack = new ItemStack(spell.SpellItem());
		SpellItemData data = new SpellItemData();
		ItemRarity rarity = (ItemRarity) WeightedUtils.WeightedRandom(ListUtils.CollectionToList(Rarities.Items));

		data.rarity = rarity.Rank();
		data.spellGUID = spell.Name();

		SpellSaving.Save(stack, data);

		return stack;

	}

	public static ItemStack Create(SpellBlueprint blueprint) {

		BaseSpell spell = blueprint.GetSpell();
		ItemStack stack = new ItemStack(spell.SpellItem());
		SpellItemData data = new SpellItemData();
		ItemRarity rarity = blueprint.GetRarity();

		data.rarity = rarity.Rank();
		data.spellGUID = spell.Name();

		SpellSaving.Save(stack, data);

		return stack;

	}
}
