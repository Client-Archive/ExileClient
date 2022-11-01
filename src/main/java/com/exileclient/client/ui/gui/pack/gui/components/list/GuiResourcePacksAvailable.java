package com.exileclient.client.ui.gui.pack.gui.components.list;

import com.exileclient.client.ExileClient;
import com.exileclient.client.ui.elements.util.GlStateManager;
import com.exileclient.client.ui.gui.pack.gui.components.ResourcePacksFolder;
import com.exileclient.client.ui.gui.pack.gui.screen.GuiResourcePacks;
import com.exileclient.client.ui.gui.pack.utils.GuiUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.ResourceLocation;

import java.util.List;


public class GuiResourcePacksAvailable extends GuiResourcePacksList {
    private static final ResourceLocation FOLDER_ICON = new ResourceLocation("client/icons/folder.png");
    private final List<ResourcePacksFolder> packFolders;
    private final boolean showPackFolders;

    public GuiResourcePacksAvailable(GuiResourcePacks parentScreen, int x, int y, int width, int height, int slotHeight, List<ResourcePackRepository.Entry> entries, List<ResourcePacksFolder> packFolders, boolean showPackFolders) {
        super(parentScreen, x, y, width, height, slotHeight, I18n.format("resourcePack.available.title"), entries);
        this.packFolders = packFolders;
        this.showPackFolders = showPackFolders;
    }

    @Override
    protected void drawSlot(int index, int right, int top, int buffer, int mouseX, int mouseY, boolean hovered) {
        if (hovered) {
            Gui.drawRect(this.left, top - 1, right + 1, top + buffer + 3, -2134851392);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
        if (this.showPackFolders) {
            if (0 <= index && index < this.packFolders.size()) {
                this.drawResourcePackDirectory(this.packFolders.get(index), top);
            } else if (this.packFolders.size() <= index && index < this.getSize()) {
                super.drawSlot(index - this.packFolders.size(), right, top, buffer, mouseX, mouseY, hovered);
            }
        } else if (0 <= index && index < this.getSize()) {
            super.drawSlot(index, right, top, buffer, mouseX, mouseY, hovered);
        }
    }

    private void drawResourcePackDirectory(ResourcePacksFolder packFolder, int top) {
        boolean showFolderIcons = ExileClient.getInstance().getGlobalSettings().showPackFolderIcons.getValue();
        if (showFolderIcons) {
            this.mc.getTextureManager().bindTexture(FOLDER_ICON);
            GlStateManager.enableBlend();
            Gui.drawScaledCustomSizeModalRect(this.left + 2, top + 3, 0.0f, 0.0f, 256, 256, 32, 32, 256.0f, 256.0f);
            GlStateManager.disableBlend();
        }
        this.mc.fontRendererObj.drawString(GuiUtils.trimString(packFolder.getName(), this.listWidth - 46), (float) this.left + (showFolderIcons ? 36.0f : 2.0f), (float) top + 2.0f, -1, true);
        int packFolders = packFolder.getPackFolders().size() - 1;
        if (packFolders != -1 && ExileClient.getInstance().getGlobalSettings().showPackFolderInfo.getValue()) {
            int entries;
            String text;
            float y = (float) top + 13.0f;
            if (packFolders != 0) {
                text = GuiUtils.trimString(packFolders + (packFolders == 1 ? " Subfolder" : " Subfolders"), this.listWidth - 46);
                this.mc.fontRendererObj.drawString(text, (float) this.left + (showFolderIcons ? 36.0f : 2.0f), y, -5592406, true);
                y += 10.0f;
            }
            if ((entries = packFolder.getEntries().size()) != 0) {
                text = GuiUtils.trimString(entries + (entries == 1 ? " Pack" : " Packs"), this.listWidth - 46);
                this.mc.fontRendererObj.drawString(text, (float) this.left + (showFolderIcons ? 36.0f : 2.0f), y, -5592406, true);
            }
        }
    }

    @Override
    protected void onClick(int index, boolean doubleClick) {
        if (this.showPackFolders) {
            if (0 <= index && index < this.packFolders.size()) {
                this.onClickResourcePackDirectory(this.packFolders.get(index), index == 0);
            } else if (this.packFolders.size() <= index && index < this.getSize()) {
                this.onClickResourcePackEntry(this.entries.get(index - this.packFolders.size()));
            }
        } else if (0 <= index && index < this.getSize()) {
            this.onClickResourcePackEntry(this.entries.get(index));
        }
    }

    private void onClickResourcePackEntry(ResourcePackRepository.Entry entry) {
        if (this.parentScreen.addSelectedEntry(entry)) {
            this.entries.remove(entry);
        }
    }

    private void onClickResourcePackDirectory(ResourcePacksFolder packFolder, boolean back) {
        if (back) {
            if (packFolder.getName().equals("Back to Main Folder")) {
                this.parentScreen.setAvailablePacks(null);
            } else if (packFolder.getPreviousPackFolder() != null) {
                this.parentScreen.setAvailablePacks(packFolder.getPreviousPackFolder());
            } else {
                this.parentScreen.setAvailablePacks(packFolder);
            }
        } else {
            this.parentScreen.setAvailablePacks(packFolder);
        }
    }

    @Override
    protected int getSize() {
        return this.showPackFolders ? this.entries.size() + this.packFolders.size() : this.entries.size();
    }
}