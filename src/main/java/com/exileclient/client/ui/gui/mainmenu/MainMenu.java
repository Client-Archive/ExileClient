package com.exileclient.client.ui.gui.mainmenu;

import com.exileclient.client.ExileClient;
import com.exileclient.client.ui.elements.button.MainMenuButton;
import com.exileclient.client.ui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class MainMenu extends MainMenuBase {

    private final MainMenuButton singlePlayerButton = new MainMenuButton("Singleplayer");
    private final MainMenuButton multiPlayerButton = new MainMenuButton("Multiplayer");
    
    private final ResourceLocation logo = new ResourceLocation("client/logo-transparent.png");

    @Override
    public void initGui() {
        this.singlePlayerButton.setSize(this.getScaledWidth() / 2.0f - 60.0F, this.getScaledHeight() / 2.0f + 5.0F, 120.0F, 18);
        this.multiPlayerButton.setSize(this.getScaledWidth() / 2.0f - 60.0F, this.getScaledHeight() / 2.0f + 29.5F, 120.0F, 18);
        super.initGui();
    }

    @Override
    public void drawMenu(float x, float y) {
        super.drawMenu(x, y);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.singlePlayerButton.handleDraw(x, y, true);
        this.multiPlayerButton.handleDraw(x, y, true);

        float size = 21.0f;
        float x2 = getScaledWidth() / 2.0f - size;
        float y2 = getScaledHeight() / 2.0f - size;
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        RenderUtil.renderEIcon(this.logo, size, x2, y2 - 37);

        ExileClient.getInstance().getResourceController().getRalewayBold20px().drawStringWithShadow("EXILE CLIENT", this.getScaledWidth() / 2.0f - 33, y2 + 7, -1);

    }

    @Override
    protected void mouseClicked(float x, float y, int button) {

        if (this.singlePlayerButton.isMouseInside(x, y)) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiSelectWorld(this));
        } else if (this.multiPlayerButton.isMouseInside(x, y)) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiMultiplayer(this));
        }

        super.mouseClicked(x, y, button);
    }
}
