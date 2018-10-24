package com.robertx22.stats.StatEffects;

import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.interfaces.IElementalEffect;
import com.robertx22.effectdatas.interfaces.IElementalPenetrable;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;

public class ElementalPeneEffect implements IStatEffect {

	@Override
	public int GetPriority() {
		return 0;
	}

	@Override
	public EffectData TryModifyEffect(EffectData Effect, Unit source, Stat stat) {

		try {
			if (Effect instanceof IElementalPenetrable && Effect.GetSource().equals(source)) {
				IElementalEffect ele = (IElementalEffect) Effect;

				if (ele.GetElement().equals(stat.Element())) {
					IElementalPenetrable ipene = (IElementalPenetrable) Effect;
					ipene.SetElementalPenetration((int) stat.Value);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Effect;
	}

}
