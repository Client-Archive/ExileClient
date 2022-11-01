package com.exileclient.client.util.layout.cosmetics.impl;

import com.exileclient.client.util.layout.cosmetics.CosmeticBase;
import com.exileclient.client.util.layout.cosmetics.CosmeticController;
import com.exileclient.client.util.layout.cosmetics.CosmeticModelBase;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CosmeticHat extends CosmeticBase {

    private final ModelHat modelHat;
    private static final ResourceLocation TEXTURE = new ResourceLocation("..."); // TODO: DESIGN TEXTURE

    public CosmeticHat(RenderPlayer renderPlayer) {
        super(renderPlayer);
        modelHat = new ModelHat(renderPlayer);
    }


    @Override
    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale) {
        if(CosmeticController.shouldRenderHat(player)) {
            GlStateManager.pushMatrix();
            playerRenderer.bindTexture(TEXTURE);

            if(player.isSneaking()) {
                GL11.glTranslated(0, 0.225D, 0);
            }

            float[] color = CosmeticController.getHatColor(player);
            GL11.glColor3d(color[0], color[1], color[2]);
            modelHat.render(player, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale);
            GL11.glColor3f(1, 1, 1);
            GL11.glPopMatrix();
        }
    }

    private class ModelHat extends CosmeticModelBase {

        private ModelRenderer rim;
        private ModelRenderer point;

        public ModelHat(RenderPlayer player) {
            super(player);
            rim = new ModelRenderer(playerModel, 0, 0);
            rim.addBox(-5.5f, -9f, -5.5f, 11, 2, 11);
            point = new ModelRenderer(playerModel, 0, 13);
            point.addBox(-3.5F, -17F, 3.5F, 7, 8, 7);
        }

        @Override
        public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale) {
            //super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale); fuck i didnt even need this
            rim.rotateAngleX = playerModel.bipedHead.rotateAngleX;
            rim.rotateAngleY = playerModel.bipedHead.rotateAngleY;
            rim.rotationPointX = 0.0F;
            rim.rotationPointY = 0.0F;
            rim.render(scale);
            point.rotateAngleX = playerModel.bipedHead.rotateAngleX;
            point.rotateAngleY = playerModel.bipedHead.rotateAngleY;
            point.rotationPointX = 0.0F;
            point.rotationPointY = 0.0F;
            point.render(scale);

        }
    }
}
