package com.exileclient.client.util;

import com.exileclient.client.util.account.SessionChanger;

public class Extra {

    public boolean isPlayerOnClient(boolean player) {
        return true; //TODO: ADD LATER
    }

    public static void login(String username, String password) {
        SessionChanger.getInstance().setUserMicrosoft(username, password);
    }

}
