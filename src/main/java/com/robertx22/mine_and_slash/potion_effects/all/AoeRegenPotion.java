package com.robertx22.mine_and_slash.potion_effects.all;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.BasePotionEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;

public class AoeRegenPotion extends BasePotionEffect {

    public static final AoeRegenPotion INSTANCE = new AoeRegenPotion();

    private AoeRegenPotion() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public String GUID() {
        return "aoe_regen";
    }

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {

        UnitData data = Load.Unit(entity);

        for (LivingEntity en : this.getEntitiesAround(entity, 3F)) {

            if (en.world.isRemote) {
                ParticleUtils.spawnHealParticles(en, 3);
            } else {

                int healed = (int) data.getUnit().healthData().val / 50;

                ResourcesData.Context ctx = new ResourcesData.Context(
                        data, en, ResourcesData.Type.MANA, healed, ResourcesData.Use.RESTORE);

                Load.Unit(en).getResources().modify(ctx);

            }
        }

    }

    @Override
    public int performEachXTicks() {
        return 40;
    }

    @Override
    public String locNameForLangFile() {
        return "Aoe Regen";
    }
}
