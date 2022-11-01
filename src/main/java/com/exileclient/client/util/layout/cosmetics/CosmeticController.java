package com.exileclient.client.util.layout.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;

public class CosmeticController {

    //TODO: We have to do a DataBase, this class will likely be rewritten!
    public static boolean shouldRenderHat(AbstractClientPlayer player) {
        return false;
    }

    public static float[] getHatColor(AbstractClientPlayer player) {
        //NOTE: RGB 0-1 ONLY!
        return new float[] {1, 0, 0};
    }

}
