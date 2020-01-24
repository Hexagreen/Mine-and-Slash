package com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;

public interface IBuffableSpell {

    enum SpellBuffType {
        None,
        Zephyr_Speed_Boost,
        Light_Aoe_Regen,
        Purity_Remove_Negative_Effects,
        Energy_Regen,
        Mana_Regen
    }

    void setBuff(SpellBuffType buff);

    SpellBuffType getBuff();

    void setBuffType(BaseSpell.SpellType type);

    BaseSpell.SpellType getBuffType();
}
