package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;

public class KeybindsRegister {

    /*
    public static KeyBinding Player_Stats = new KeyBinding("Player Stat Overview", GLFW.GLFW_KEY_P, Ref.MOD_NAME);
    public static KeyBinding Player_Stat_Points = new KeyBinding(
            "Stat Allocation Screen", GLFW.GLFW_KEY_O, Ref.MOD_NAME);
    public static KeyBinding Talent_Tree = new KeyBinding("Talent Tree", GLFW.GLFW_KEY_J, Ref.MOD_NAME);
    public static KeyBinding disableNeatOverlay = new KeyBinding(
            "Disable Neat Overlay", GLFW.GLFW_KEY_MINUS, Ref.MOD_NAME);
    public static KeyBinding mapInfo = new KeyBinding("Adventure Map Info", GLFW.GLFW_KEY_M, Ref.MOD_NAME);


     */

    public static KeyBinding hubScreen = new KeyBinding("Main Hub Screen", GLFW.GLFW_KEY_H, Ref.MOD_NAME);
    public static KeyBinding swapHotbar = new KeyBinding("Swap Spell Hotbar", GLFW.GLFW_KEY_LEFT_ALT, Ref.MOD_NAME);

    static String HOTBAR_NAME = Ref.MOD_NAME + " Hotbar";

    static KeyBinding SPELL_1 = hotbar(1, GLFW.GLFW_KEY_R);
    static KeyBinding SPELL_2 = hotbar(2, GLFW.GLFW_KEY_G);
    static KeyBinding SPELL_3 = hotbar(3, GLFW.GLFW_KEY_X);
    static KeyBinding SPELL_4 = hotbar(4, GLFW.GLFW_KEY_C);
    static KeyBinding SPELL_5 = hotbar(5, GLFW.GLFW_KEY_V);

    public static KeyBinding hotbar(int num, int keycode) {
        return new KeyBinding("Spell Hotbar Key " + num, KeyConflictContext.IN_GAME,
                              InputMappings.Type.KEYSYM, keycode, HOTBAR_NAME
        );
    }

    public static HashMap<KeyBinding, Integer> HOTBAR = new HashMap<KeyBinding, Integer>() {{
        put(SPELL_1, 0);
        put(SPELL_2, 1);
        put(SPELL_3, 2);
        put(SPELL_4, 3);
        put(SPELL_5, 4);

    }};

    public static HashMap<Integer, KeyBinding> HOTBAR_BY_NUMBER = new HashMap<Integer, KeyBinding>() {{
        put(0, SPELL_1);
        put(1, SPELL_2);
        put(2, SPELL_3);
        put(3, SPELL_4);
        put(4, SPELL_5);

    }};

    public static void register() {
        /*
        ClientRegistry.registerKeyBinding(Player_Stats);
        ClientRegistry.registerKeyBinding(Player_Stat_Points);
        ClientRegistry.registerKeyBinding(disableNeatOverlay);
        ClientRegistry.registerKeyBinding(Talent_Tree);
        ClientRegistry.registerKeyBinding(mapInfo);

         */
        ClientRegistry.registerKeyBinding(hubScreen);
        ClientRegistry.registerKeyBinding(swapHotbar);

        for (KeyBinding entry : HOTBAR.keySet()) {
            ClientRegistry.registerKeyBinding(entry);
        }

    }

}
