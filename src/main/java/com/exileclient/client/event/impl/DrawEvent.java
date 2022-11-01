package com.exileclient.client.event.impl;

import com.exileclient.client.event.EventBus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.client.gui.ScaledResolution;

@AllArgsConstructor @Getter
public class DrawEvent extends EventBus.Event {

    private ScaledResolution scaledRes;

}
