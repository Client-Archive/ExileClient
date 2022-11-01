package com.exileclient.client.util.layout.gui;

import com.exileclient.client.ExileClient;
import com.exileclient.client.ui.util.RoundedUtils;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class MainMenu extends GuiScreen {
    @Override
    public void initGui() {
        ExileClient.getInstance().getDiscordRP().update("Idle", "In the menus...");
        GuiButton changeButton = new GuiButton(4, 4, 16, "Change");
        changeButton.setWidth(fontRendererObj.getStringWidth("Change") + 4);
        this.buttonList.add(new GuiButton(0, this.width/2-100, this.height / 2-50 + 40, "Singleplayer"));
        this.buttonList.add(new GuiButton(1, this.width/2-100, this.height / 2-50+ 65, "Multiplayer"));
        this.buttonList.add(new GuiButton(2, this.width/2-100, this.height / 2-50 + 90, "Options"));
        this.buttonList.add(new GuiButton(3, this.width/2-100, this.height / 2-50 + 115, "Exit game"));
        //this.buttonList.add(new LunarButton());
        this.buttonList.add(changeButton);

        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        FontRenderer fr = mc.fontRendererObj;
        ScaledResolution sr = new ScaledResolution(mc);
        int hW = sr.getScaledWidth() - (sr.getScaledWidth()) / 2;
        int hH = sr.getScaledHeight() - (sr.getScaledHeight()) / 2;
        mc.getTextureManager().bindTexture(new ResourceLocation("com/MainMenu.png"));
        this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
        RoundedUtils.drawRoundedRect(hW - 50, hH - 25, hW + 50, hH + 25, 69, 0x90000000);
        this.fontRendererObj.drawString("Logged in as " +  mc.getSession().getUsername(), 6, 4, -1);
        this.fontRendererObj.drawString("Exile Client (?/?)", 4, this.height-12, -1);
        this.fontRendererObj.drawString("Developed by Exile Team with <3", this.width - fontRendererObj.getStringWidth("Developed by Exile Team") -4, this.height-12, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id == 0) {
            mc.displayGuiScreen(new GuiSelectWorld(this));
        }
        if(button.id == 1) {
        mc.displayGuiScreen(new GuiMultiplayer(this));
        }
        if(button.id == 2) {
         mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
        }
        if(button.id == 3) {
            mc.shutdown();
        }
        if(button.id == 4) {
            mc.displayGuiScreen(new AccountManager());
        }
        super.actionPerformed(button);
    }
}
