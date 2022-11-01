package com.exileclient.client.util.layout.cosmetics;

import lombok.AllArgsConstructor;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

@AllArgsConstructor
public abstract class CosmeticBase implements LayerRenderer<AbstractClientPlayer> {

    protected final RenderPlayer playerRenderer;

    @Override
    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale) {
        if(player.hasPlayerInfo() && !player.isInvisible()) {
            render(player, limbSwing, limbSwingAmount, partialTicks, ageInTicks, headYaw, headPitch, scale);

        }
    }

    public abstract void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale);

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

}
