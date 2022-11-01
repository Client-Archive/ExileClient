package com.exileclient.client.util.gui;

import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.util.ResourceLocation;

import java.io.InputStream;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class GuiUtil {

    public static InputStream getResourceStreamerino(ResourceLocation location) {
        return DefaultResourcePack.class.getResourceAsStream("/assets/" + location.getResourceDomain() + "/" + location.getResourcePath());
    }

}
