package com.exileclient.client.module.impl.hud;

import com.exileclient.client.event.impl.RenderEvent;
import com.exileclient.client.module.AbstractModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class ModuleFPS extends AbstractModule {

    public ModuleFPS() {
        super("FPS", "Shows your ingame FPS.");
        this.setState(true);
        this.addEvent(RenderEvent.class, this::render);
    }

    private void render(RenderEvent event) {
        System.out.println("a");
        String text = Minecraft.debugFPS + " FPS";

        GL11.glPushMatrix();
        this.scale(event.getScaledResolution());
        this.setDimensions(56, 18);

        Gui.drawRect(0.0f, 0.0f, 56, 13, 0x6F000000);
        this.mc.fontRendererObj.drawString(text, (int) (this.width / 2.0f - (float) (this.mc.fontRendererObj.getStringWidth(text) / 2)), 3, -1);
        GL11.glPopMatrix();

    }
}
