package com.exileclient.client.event.impl;

import com.exileclient.client.event.EventBus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.client.gui.ScaledResolution;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
@AllArgsConstructor
@Getter
public class RenderEvent extends EventBus.Event {
    private ScaledResolution scaledResolution;
}
