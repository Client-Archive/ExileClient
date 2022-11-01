package com.exileclient.client.controller.impl.resource;

import com.exileclient.client.controller.Controller;
import com.exileclient.client.util.font.NFontRenderer;
import lombok.Getter;
import net.minecraft.util.ResourceLocation;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
@Getter
public class ResourceController extends Controller {

    private NFontRenderer ubuntuM14px;
    private NFontRenderer ubuntuM16px;

    private NFontRenderer playBold14px;
    private NFontRenderer playBold16px;
    private NFontRenderer playBold20px;

    private NFontRenderer ralewayBold14px;
    private NFontRenderer ralewayBold16px;
    private NFontRenderer ralewayBold20px;

    @Override
    public void onLoad() {
        this.ubuntuM14px = new NFontRenderer(new ResourceLocation("client/font/Ubuntu-M.ttf"), 14.0f);
        this.ubuntuM16px = new NFontRenderer(new ResourceLocation("client/font/Ubuntu-M.ttf"), 16.0f);

        this.playBold14px = new NFontRenderer(new ResourceLocation("client/font/Play-Bold.ttf"), 14.0F);
        this.playBold16px = new NFontRenderer(new ResourceLocation("client/font/Play-Bold.ttf"), 16.0F);
        this.playBold20px = new NFontRenderer(new ResourceLocation("client/font/Play-Bold.ttf"), 20.0F);

        this.ralewayBold14px = new NFontRenderer(new ResourceLocation("client/font/Raleway-Black.ttf"), 14.0F);
        this.ralewayBold16px = new NFontRenderer(new ResourceLocation("client/font/Raleway-Black.ttf"), 16.0F);
        this.ralewayBold20px = new NFontRenderer(new ResourceLocation("client/font/Raleway-Black.ttf"), 20.0F);

    }

}
