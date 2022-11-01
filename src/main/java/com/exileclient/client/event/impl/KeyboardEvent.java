package com.exileclient.client.event.impl;

import com.exileclient.client.event.EventBus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class KeyboardEvent extends EventBus.Event {

    private int key;

}
