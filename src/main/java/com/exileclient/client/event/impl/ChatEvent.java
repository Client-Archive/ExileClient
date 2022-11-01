package com.exileclient.client.event.impl;

import com.exileclient.client.event.EventBus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ChatEvent extends EventBus.Event {

    private final String receivedChatMessage;

}
