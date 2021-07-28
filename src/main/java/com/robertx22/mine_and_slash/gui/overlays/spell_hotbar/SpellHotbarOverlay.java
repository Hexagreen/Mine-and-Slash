package com.robertx22.mine_and_slash.gui.overlays.spell_hotbar;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.client.KeybindsRegister;
import com.robertx22.mine_and_slash.onevent.ontick.OnClientTick;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCastingData;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Locale;

public class SpellHotbarOverlay extends AbstractGui {

    public static SpellCastingData.Hotbar CURRENT_HOTBAR = SpellCastingData.Hotbar.FIRST;

    private static final ResourceLocation HOTBAR_TEX = new ResourceLocation(Ref.MODID,
        "textures/gui/spells/hotbar.png"
    );
    private static final ResourceLocation COOLDOWN_TEX = new ResourceLocation(Ref.MODID,
        "textures/gui/spells/cooldown.png"
    );
    private static final ResourceLocation SPELL_READY_TEXT = new ResourceLocation(Ref.MODID,
        "textures/gui/spells/spell_ready.png"
    );
    static int WIDTH = 22;
    static int HEIGHT = 102;

    Minecraft mc = Minecraft.getInstance();

    PlayerSpellCap.ISpellsCap data;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderPlayerOverlay(RenderGameOverlayEvent event) {

        if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }

        if (mc.player.isSpectator()) {
            return;
        }

        data = Load.spells(mc.player);

        int x = 0;
        int y = (int) (mc.mainWindow.getScaledHeight() / 2 - HEIGHT / 2);

        RenderSystem.enableBlend(); // enables transparency

        renderHotbar(x, y);
        renderSpellsOnHotbar(x, y);

        RenderSystem.disableBlend(); // enables transparency

    }

    private void renderSpellsOnHotbar(int x, int y) {

        x += 3;
        y += 3;

        for (int i = 0; i < 5; i++) {
            BaseSpell spell = data.getSpellByKeybind(i, CURRENT_HOTBAR);

            if (spell != null) {
                double scale = 0.5D;
                RenderSystem.scaled(scale, scale, scale);

                int xs = (int) (x * 1 / scale);
                int ys = (int) (y * 1 / scale);

                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

                mc.getTextureManager()
                    .bindTexture(spell.getIconLoc());
                this.blit(xs, ys, 0, 0, 32, 32, 32, 32);

                SpellData spelldata = data.getCastingData()
                    .getDataBySpell(spell);

                if (spelldata != null) {
                    if (spelldata.cooldownIsReady() == false) {

                        float percent = (float) spelldata.getRemainingCooldown() / (float) spelldata.getTotalCooldown();

                        percent = MathHelper.clamp(percent, 0, 1F);

                        mc.getTextureManager()
                            .bindTexture(COOLDOWN_TEX);
                        this.blit(xs, ys, 0, 0, 32, (int) (32 * percent), 32, 32);

                    }
                }

                RenderSystem.scaled(1 / scale, 1 / scale, 1 / scale);

                if (spelldata != null) {
                    if (spelldata.cooldownIsReady()) {
                        if (OnClientTick.COOLDOWN_READY_MAP.getOrDefault(spell.GUID(), 0) > 0) {

                            RenderSystem.enableBlend(); // enables transparency
                            mc.getTextureManager()
                                .bindTexture(SPELL_READY_TEXT);
                            this.blit(x - 2, y - 2, 0, 0, 20, 20, 20, 20);
                            RenderSystem.disableBlend(); // enables transparency

                        }
                    }
                }
                String txt = KeybindsRegister.HOTBAR_BY_NUMBER.get(i).toString().toUpperCase(Locale.ROOT);
                if (txt.length() > 3) {
                    txt = txt.substring(0, 2);
                }
                GuiUtils.renderScaledText(xs+23, ys+23, 1.4, txt, TextFormatting.GREEN);
            }
            y += 20;
        }
    }

    private void renderHotbar(int x, int y) {

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager()
            .bindTexture(HOTBAR_TEX);

        this.blit(x, y, 0, 0, WIDTH, HEIGHT);

    }
}