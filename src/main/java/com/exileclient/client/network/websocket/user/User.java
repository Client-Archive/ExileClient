package com.exileclient.client.network.websocket.user;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {

    public UUID mcName;
    public boolean isUser;

}
