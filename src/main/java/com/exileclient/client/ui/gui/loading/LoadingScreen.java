package com.exileclient.client.ui.gui.loading;

import com.exileclient.client.ExileClient;
import com.exileclient.client.ui.gui.AbstractGUI;
import com.exileclient.client.ui.util.RenderUtil;
import com.exileclient.client.util.font.NFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class LoadingScreen extends AbstractGUI {

    private final ResourceLocation logo = new ResourceLocation("client/logo-transparent.png");
    private final NFontRenderer font = new NFontRenderer(new ResourceLocation("client/font/Ubuntu-M.ttf"), 14.0f);
    private final NFontRenderer logoFont = new NFontRenderer(new ResourceLocation("client/font/Raleway-Black.ttf"), 20.0F);

    private final Framebuffer framebuffer;

    public int totalPhases;
    private int currentPhase;

    private String currentText;

    /**
     * Initial setup.
     */
    public LoadingScreen(int phases) {
        this.totalPhases = phases;
        this.mc = Minecraft.getMinecraft();
        this.scaledResolution = new ScaledResolution(this.mc);
        this.framebuffer = new Framebuffer(this.scaledResolution.getScaledWidth() * this.scaledResolution.getScaleFactor(), this.scaledResolution.getScaledHeight() * this.scaledResolution.getScaleFactor(), true);
    }

    /**
     * Aero Client's loading screen (requested by yawfi)
     */
    @Override
    public void drawMenu(float x, float y) {
        this.setFrameBuffer();

        float scale = this.getScaleFactor();
        float width = this.scaledResolution.getScaledWidth() / scale;
        float height = this.scaledResolution.getScaledHeight() / scale;

        GL11.glScaled(scale, scale, scale);
        Gui.drawRect(0, 0, width, height, -15395563);

        this.drawLogo(width, height);

        this.logoFont.drawStringWithShadow("EXILE CLIENT", width / 2.0f - 33, height / 2.0f - 14, -1);

        float barWidth = 160.0f;
        double barHeight = 2.0;
        float barX = width / 2.0f - 80.0f;
        float barY = height - 60.0f;
        float yOffset = 5.0F;
        RenderUtil.drawRoundedRect(barX, barY, barX + barWidth, barY + yOffset, barHeight, -14671840);

        if (this.currentText != null) {
            this.font.drawCenteredString(this.currentText, width / 2.0f, barY - 11.0f, -3092272);
        }

        float minWidth = barWidth * ((float) this.currentPhase / (float) this.totalPhases);
        RenderUtil.drawRoundedRect(barX, barY, barX + minWidth + 12, barY + yOffset, barHeight, 0xFF462EE3);

        this.initFrameBufferRenderer();
        this.mc.updateDisplay();
    }

    @Override
    protected void mouseClicked(float x, float y, int button) {
        // empty on purpose
    }

    @Override
    public void mouseMovedOrUp(float x, float y, int button) {
        // empty on purpose
    }

    /**
     * Draws the logo.
     */
    private void drawLogo(float resWidth, float resHeight) {
        float size = 21.0f;
        float x = resWidth / 2.0f - size;
        float y = resHeight / 2.0f - size;
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        RenderUtil.renderEIcon(this.logo, size, x, y - 37);
    }

    public void updatePhase(String phase) {
        this.currentText = phase;
        ++this.currentPhase;
        if (this.totalPhases <= this.currentPhase) {
            this.totalPhases = this.currentPhase + 1;
        }
        this.drawMenu(0.0f, 0.0f);
    }

    /**
     * Sets the frame buffer.
     */
    private void setFrameBuffer() {
        try {
            this.framebuffer.bindFramebuffer(false);
            GL11.glMatrixMode(5889);
            GL11.glLoadIdentity();
            GL11.glOrtho(0.0, this.scaledResolution.getScaledWidth(), this.scaledResolution.getScaledHeight(), 0.0, 1000.0, 3000.0);
            GL11.glMatrixMode(5888);
            GL11.glLoadIdentity();
            GL11.glTranslatef(0.0f, 0.0f, -2000.0f);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_FOG);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
        } catch (RuntimeException ignored) {
        }
    }

    /**
     * Initializes the frame buffer renderer.
     */
    private void initFrameBufferRenderer() {
        int scaleFactor = this.scaledResolution.getScaleFactor();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        this.framebuffer.unbindFramebuffer();
        this.framebuffer.framebufferRender(this.scaledResolution.getScaledWidth() * scaleFactor, this.scaledResolution.getScaledHeight() * scaleFactor);
        GL11.glAlphaFunc(516, 0.1f);
        GL11.glFlush();
    }
}
