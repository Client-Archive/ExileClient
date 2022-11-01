package com.exileclient.client.event.impl;

import com.exileclient.client.event.EventBus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
@Getter
@AllArgsConstructor
public class ClickEvent extends EventBus.Event {

    private final int mouseButton;

}
