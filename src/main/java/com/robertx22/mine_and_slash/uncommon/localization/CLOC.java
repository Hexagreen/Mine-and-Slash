package com.robertx22.mine_and_slash.uncommon.localization;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.util.text.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class CLOC {

    public static String translate(ITextComponent s) {
        return MMORPG.proxy.translate(s);
    }

    private static ITextComponent base(String s) {
        if (s.isEmpty()) {
            return new StringTextComponent("");
        } else {
            return new TranslationTextComponent(s);
        }
    }

    public static ITextComponent tooltip(String str) {

        return base(Ref.MODID + ".tooltip." + str);

    }

    public static ITextComponent lore(String str) {

        return new StringTextComponent(TextFormatting.GREEN + "'").appendSibling(base(Ref.MODID + ".lore." + str)
                .appendSibling(new StringTextComponent("'")));

    }

    public static ITextComponent blank(String string) {
        return base(string);
    }

    public static List<ITextComponent> longDesc(String s) {
        List<ITextComponent> res = new ArrayList<>();

        String[] str = base(s).getString().split("\n");

        for (String value : str) {
            res.add(new SText(value));
        }

        return res;
    }
}
