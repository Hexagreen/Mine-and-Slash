package com.robertx22.mine_and_slash.gui.screens.bestiary;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.talent_tree.RenderUtils;
import com.robertx22.mine_and_slash.gui.BaseScrollbar;
import com.robertx22.mine_and_slash.gui.bases.BaseScreen;
import com.robertx22.mine_and_slash.gui.bases.INamedScreen;
import com.robertx22.mine_and_slash.gui.screens.bestiary.groups.BestiaryGroup;
import com.robertx22.mine_and_slash.gui.screens.bestiary.groups.UniqueGearBestiary;
import com.robertx22.mine_and_slash.gui.screens.main_hub.MainHubScreen;
import com.robertx22.mine_and_slash.gui.screens.spell_hotbar_setup.SpellHotbatSetupScreen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BestiaryScreen extends BaseScreen implements INamedScreen {

    ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/bestiary/bestiary.png");
    ResourceLocation BUTTON_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/bestiary/buttons.png");
    ResourceLocation SPLITTER_BUTTON_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/bestiary/split.png");
    ResourceLocation GROUP_BUTTON_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/bestiary/bestiary_group_buttons.png");

    public Minecraft mc;

    public static int entryButtonX = 235;
    public static int entryButtonY = 24;

    public static int groupButtonX = 20;
    public static int groupButtonY = 20;

    Scrollbar scrollbar;

    static int x = 276;
    static int y = 200;

    public BestiaryGroup currentBestiaryGroup = new UniqueGearBestiary();

    int currentElement = 0;

    public List<BestiaryEntry> entries = new ArrayList<>();

    int level = 1;
    int elementsAmount = 1;

    int entryButtonsAtOnce = 7;

    public BestiaryScreen() {
        super(x, y);
        this.mc = Minecraft.getInstance();

    }

    @Override
    protected void init() {
        super.init();

        addButton(new BackButton(guiLeft, guiTop - BackButton.ySize));

        this.level = Load.Unit(mc.player)
            .getLevel();

        initEntries();

        setupEntryButtons();
        setupGroupButtons();
        setupScrollbar();

    }

    public void setupScrollbar() {

        int sliderXSize = 10;
        int sliderYSize = 30;

        int sliderX = guiLeft + 262;
        int sliderY = guiTop + 18;

        scrollbar = addButton(new Scrollbar(sliderX, sliderY, 174));

        // AbstractSlider
    }

    public void initEntries() {
        this.entries = currentBestiaryGroup.getDefaultSplitter()
            .split(level);
        this.elementsAmount = entries.size();
    }

    public void setupGroupButtons() {

        int gx = guiLeft + 5;
        int gy = guiTop + 18;

        for (BestiaryGroup bestiaryGroup : BestiaryGroup.getAll()) {
            addButton(new GroupButton(this, bestiaryGroup, gx, gy));
            gy += groupButtonY;
        }

    }

    public void setupEntryButtons() {

        this.buttons.removeIf(x -> x instanceof EntryButton || x instanceof SplitterButton);

        int x = this.guiLeft + 27;
        int y = this.guiTop + 19;

        for (int i = currentElement; i < currentElement + entryButtonsAtOnce; i++) {
            if (i >= elementsAmount) {
                continue;
            } else {

                BestiaryEntry entry = entries.get(i);

                if (entry instanceof BestiaryEntry.Splitter) {
                    addButton(new SplitterButton((BestiaryEntry.Splitter) entry, x, y));
                    y += entryButtonY + 0;
                } else if (entry instanceof BestiaryEntry.Item) {
                    addButton(new EntryButton((BestiaryEntry.Item) entry, x, y));
                    y += entryButtonY + 0;
                }

            }

        }

    }

    @Override
    public void render(int x, int y, float ticks) {

        drawBackground(ticks, x, y);

        super.render(x, y, ticks);

        this.buttons.forEach(b -> b.renderToolTip(x, y));

    }

    protected void drawBackground(float partialTicks, int x, int y) {
        Minecraft.getInstance()
            .getTextureManager()
            .bindTexture(BACKGROUND_TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        blit(guiLeft, guiTop, this.getBlitOffset(), 0.0F, 0.0F, this.x, this.y, 256, 512);
    }

    @Override
    public ResourceLocation iconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/main_hub/icons/bestiary.png");
    }

    @Override
    public Words screenName() {
        return Words.Compendium;
    }

    @Override
    public boolean mouseScrolled(double num1, double num2, double num3) {

        this.setCurrentElement((int) (currentElement - num3));

        return super.mouseScrolled(num1, num2, num3);

    }

    public void setCurrentElement(int element) {

        this.currentElement = MathHelper.clamp(element, 0, elementsAmount);

        scrollbar.setValueFromElement(currentElement, elementsAmount);

        setupEntryButtons();

    }

    @Override
    public boolean mouseReleased(double m1, double m2, int m3) {
        this.setDragging(false);

        buttons.stream()
            .filter(x -> x.isMouseOver(m1, m2))
            .findFirst()
            .ifPresent(x -> x.onClick(m1, m2));

        return super.mouseReleased(m1, m2, m3);

    }

    class SplitterButton extends ImageButton {
        BestiaryEntry.Splitter splitter;

        public SplitterButton(BestiaryEntry.Splitter splitter, int xPos, int yPos) {
            super(xPos, yPos, entryButtonX, entryButtonY, 0, 0, entryButtonY, SPLITTER_BUTTON_TEXTURE, (button) -> {
            });

            this.splitter = splitter;

        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            super.renderButton(x, y, ticks);

            int xp = (int) (this.x + 10);
            int yp = (int) (this.y + entryButtonY / 2) - mc.fontRenderer.FONT_HEIGHT / 2;

            mc.fontRenderer.drawStringWithShadow(splitter.splitReason, xp, yp, TextFormatting.YELLOW.getColor());

        }

    }

    public void setGroup(BestiaryGroup group) {
        this.currentBestiaryGroup = group;

        initEntries();

        this.setCurrentElement(0);

        this.setupEntryButtons();

    }

    class Scrollbar extends BaseScrollbar {

        protected Scrollbar(int xpos, int ypos, int scrollbarTotalHeight) {
            super(xpos, ypos, scrollbarTotalHeight);
        }

        @Override
        protected void applyValue() {
            BestiaryScreen.this.setCurrentElement((int) (this.value * BestiaryScreen.this.elementsAmount));
        }
    }

    class GroupButton extends ImageButton {
        BestiaryGroup group;
        BestiaryScreen screen;

        public GroupButton(BestiaryScreen screen, BestiaryGroup group, int xPos, int yPos) {
            super(xPos, yPos, groupButtonX, groupButtonY, 0, 0, groupButtonY, GROUP_BUTTON_TEXTURE, (button) -> {
            });

            this.screen = screen;
            this.group = group;

        }

        @Override
        public void onPress() {
            super.onPress();

            screen.setGroup(group);

        }

        @Override
        public void renderToolTip(int x, int y) {
            if (this.isHovered) {
                BestiaryScreen.this.renderTooltip(
                    Arrays.asList(TextFormatting.BLUE + "" + TextFormatting.BOLD +
                        CLOC.translate(group.getName())), x, y, Minecraft.getInstance().fontRenderer);
            }
        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            super.renderButton(x, y, ticks);
            RenderUtils.render16Icon(group.getTextureLoc(), this.x + 2, this.y + 2);
        }

    }

    class EntryButton extends ImageButton {
        BestiaryEntry.Item item;

        public EntryButton(BestiaryEntry.Item item, int xPos, int yPos) {
            super(xPos, yPos, entryButtonX, entryButtonY, 0, 0, entryButtonY, BUTTON_TEXTURE, (button) -> {
            });

            this.item = item;

        }

        int getStackY() {
            return this.y + 4;
        }

        int getStackX() {
            return this.x + 13;
        }

        @Override
        public void renderButton(int x, int y, float ticks) {

            super.renderButton(x, y, ticks);

            if (item != null) {
                mc.getItemRenderer()
                    .renderItemAndEffectIntoGUI(item.stack, getStackX(), getStackY());

                int xp = (int) (this.x + 35);
                int yp = (int) (this.y + entryButtonY / 2) - mc.fontRenderer.FONT_HEIGHT / 2;

                mc.fontRenderer.drawStringWithShadow(item.getName(), xp, yp, TextFormatting.GREEN.getColor());

            }

        }

        @Override
        public void renderToolTip(int x, int y) {
            if (GuiUtils.isInRect(getStackX(), getStackY(), 18, 18, x, y)) {
                BestiaryScreen.this.renderTooltip(item.stack, x, y);
            }
        }
    }

    static ResourceLocation BACK_BUTTON = new ResourceLocation(
            Ref.MODID, "textures/gui/spell_schools/back_button.png");

    static class BackButton extends ImageButton {
        public static int xSize = 26;
        public static int ySize = 16;

        public BackButton(int xPos, int yPos) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, BACK_BUTTON, (button) -> {
                Minecraft.getInstance()
                        .displayGuiScreen(new MainHubScreen());

            });
        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            super.renderButton(x, y, ticks);
        }

    }

}

