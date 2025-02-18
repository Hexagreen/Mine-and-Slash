package com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics;

import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.onevent.entity.damage.DamageEventData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class HammerWeaponMechanic extends WeaponMechanic {

    @Override
    public List<ITextComponent> tooltipDesc() {
        return Arrays.asList(
            new StringTextComponent(TextFormatting.LIGHT_PURPLE + Words.HammerAOEDesc_1.locName().getString()),
            new StringTextComponent(TextFormatting.LIGHT_PURPLE + Words.HammerAOEDesc_2.locName().getString())
        );
    }

    @Override
    public void doSpecialAttack(DamageEventData data) {

        int num = (int) data.sourceData.getUnit()
            .getCreateStat(PhysicalDamage.GUID)
            .getRandomRangeValue();

        List<LivingEntity> targets = EntityFinder.start(data.source, LivingEntity.class, data.target.getPositionVector())
            .radius(1.5F)
            .build();

        if (targets.size() > 5) {
            targets = targets.subList(0, 5);
        }

        //if (targets.size() == 1) {
        //    num *= 2;
        //}

        for (LivingEntity en : targets) {

            if (en.equals(data.target)) {
                DamageEffect dmg = new DamageEffect(data.event, data.source, en, num, data.sourceData, data.targetData,
                    EffectData.EffectTypes.BASIC_ATTACK, WeaponTypes.Hammer
                );
                dmg.Activate();
            } else {
                DamageEffect dmg = new DamageEffect(null, data.source, en, (int) (num * 0.5F), data.sourceData, data.targetData,
                    EffectData.EffectTypes.BASIC_ATTACK, WeaponTypes.Hammer
                );
                dmg.Activate();
            }

        }
    }

    @Override
    public String GUID() {
        return "hammer";
    }
}
