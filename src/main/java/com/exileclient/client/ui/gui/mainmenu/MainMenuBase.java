package com.exileclient.client.ui.gui.mainmenu;

import com.exileclient.client.ui.gui.AbstractGUI;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class MainMenuBase extends AbstractGUI {

    private static int rotAng = 4100;

    private ResourceLocation backgroundTexture;

    private final ResourceLocation[] panoramaPaths = new ResourceLocation[]{
            new ResourceLocation("client/panorama/0.png"),
            new ResourceLocation("client/panorama/1.png"),
            new ResourceLocation("client/panorama/2.png"),
            new ResourceLocation("client/panorama/3.png"),
            new ResourceLocation("client/panorama/4.png"),
            new ResourceLocation("client/panorama/5.png")
    };

    @Override
    public void initGui() {
        super.initGui();
        DynamicTexture viewportTexture = new DynamicTexture(256, 256);
        this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", viewportTexture);
    }

    @Override
    public void drawMenu(float x, float y) {
        this.mc.ingameGUI.drawGradientRect(0.0f, 0.0f, this.getScaledWidth(), this.getScaledHeight(), 0x5FFFFFFF, 0x2FFFFFFF);
        this.mc.ingameGUI.drawGradientRect(0.0f, 0.0f, this.getScaledWidth(), 160.0f, -553648128, 0);
    }

    @Override
    protected void mouseClicked(float x, float y, int button) {
    }

    @Override
    public void mouseMovedOrUp(float x, float y, int button) {
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        rotAng++;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.draw(mouseX, mouseY, 1.0f);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void draw(int var1, int var2, float var3) {
        this.mc.getFramebuffer().unbindFramebuffer();

        GL11.glViewport(0, 0, 256, 256);

        this.drawPanorama(var1, var2, var3);
        this.rotateAndBlurSkybox(var3);
        this.rotateAndBlurSkybox(var3);
        this.rotateAndBlurSkybox(var3);
        this.rotateAndBlurSkybox(var3);
        this.rotateAndBlurSkybox(var3);
        this.rotateAndBlurSkybox(var3);
        this.rotateAndBlurSkybox(var3);
        this.mc.getFramebuffer().bindFramebuffer(true);

        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);

        float var5 = this.width > this.height ? 120.0f / (float) this.width : 120.0f / (float) this.height;
        float var6 = (float) this.height * var5 / 256.0f;
        float var7 = (float) this.width * var5 / 256.0f;
        float var8 = this.width;
        float var9 = this.height;

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();

        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        worldrenderer.pos(0.0, var9, zLevel).tex(0.5f - var6, 0.5f + var7).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        worldrenderer.pos(var8, var9, zLevel).tex(0.5f - var6, 0.5f - var7).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        worldrenderer.pos(var8, 0.0, zLevel).tex(0.5f + var6, 0.5f - var7).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        worldrenderer.pos(0.0, 0.0, zLevel).tex(0.5f + var6, 0.5f + var7).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        tessellator.draw();
    }

    private void drawPanorama(int var1, int var2, float var3) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GL11.glMatrixMode(5889);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        Project.gluPerspective(120.0f, 1.0f, 0.05f, 10.0f);
        GL11.glMatrixMode(5888);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glDisable(2884);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        int var5 = 8;
        for (int var6 = 0; var6 < var5 * var5; ++var6) {
            GL11.glPushMatrix();
            float var7 = ((float) (var6 % var5) / (float) var5 - 0.5f) / 64.0f;
            float var8 = ((float) (var6 / var5) / (float) var5 - 0.5f) / 64.0f;
            float var9 = 0.0f;
            GL11.glTranslatef(var7, var8, var9);
            GL11.glRotatef(MathHelper.sin(((float) rotAng + var3) / 400.0f) * 25.0f + 20.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(-((float) rotAng + var3) * 0.39240506f * 0.2548387f, 0.0f, 1.0f, 0.0f);
            for (int var10 = 0; var10 < 6; ++var10) {
                GL11.glPushMatrix();
                if (var10 == 1) {
                    GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                }
                if (var10 == 2) {
                    GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                }
                if (var10 == 3) {
                    GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
                }
                if (var10 == 4) {
                    GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                }
                if (var10 == 5) {
                    GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                }
                this.mc.getTextureManager().bindTexture(this.panoramaPaths[var10]);
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                float var11 = 0.0f;
                worldrenderer.pos(-1.0, -1.0, 1.0).tex(0.0f + var11, 0.0f + var11).color(255, 255, 255, 255 / (var6 + 1)).endVertex();
                worldrenderer.pos(1.0, -1.0, 1.0).tex(1.0f - var11, 0.0f + var11).color(255, 255, 255, 255 / (var6 + 1)).endVertex();
                worldrenderer.pos(1.0, 1.0, 1.0).tex(1.0f - var11, 1.0f - var11).color(255, 255, 255, 255 / (var6 + 1)).endVertex();
                worldrenderer.pos(-1.0, 1.0, 1.0).tex(0.0f + var11, 1.0f - var11).color(255, 255, 255, 255 / (var6 + 1)).endVertex();
                tessellator.draw();
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
            GL11.glColorMask(true, true, true, false);
        }
        GL11.glColorMask(true, true, true, true);
        GL11.glMatrixMode(5889);
        GL11.glPopMatrix();
        GL11.glMatrixMode(5888);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(2884);
        GL11.glEnable(2929);
    }

    private void rotateAndBlurSkybox(float var1) {
        this.mc.getTextureManager().bindTexture(this.backgroundTexture);
        GL11.glTexParameteri(3553, 10241, 9729);
        GL11.glTexParameteri(3553, 10240, 9729);
        GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
        GL11.glEnable(3042);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColorMask(true, true, true, false);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        GL11.glDisable(3008);
        int var3 = 3;
        for (int var4 = 0; var4 < var3; ++var4) {
            float var5 = this.width;
            float var6 = this.height;
            float var7 = (float) (var4 - var3 / 2) / 256.0f;
            worldrenderer.pos(var5, var6, zLevel).tex(0.0f + var7, 1.0).color(1.0f, 1.0f, 1.0f, 1.0f / (float) (var4 + 1)).endVertex();
            worldrenderer.pos(var5, 0.0, zLevel).tex(1.0f + var7, 1.0).color(1.0f, 1.0f, 1.0f, 1.0f / (float) (var4 + 1)).endVertex();
            worldrenderer.pos(0.0, 0.0, zLevel).tex(1.0f + var7, 0.0).color(1.0f, 1.0f, 1.0f, 1.0f / (float) (var4 + 1)).endVertex();
            worldrenderer.pos(0.0, var6, zLevel).tex(0.0f + var7, 0.0).color(1.0f, 1.0f, 1.0f, 1.0f / (float) (var4 + 1)).endVertex();
        }
        tessellator.draw();
        GL11.glEnable(3008);
        GL11.glColorMask(true, true, true, true);
    }

}
