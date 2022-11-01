package com.exileclient.client.controller.impl.anticheat;

import com.exileclient.client.controller.impl.anticheat.check.player.TimerCheck;

import java.util.ArrayList;

public class AntiCheatManager {

    public ArrayList<AntiCheat> checks = new ArrayList<>();

    public AntiCheatManager() {
        this.checks.add(new TimerCheck());
    }

    public AntiCheat getCheckByName(String name) {
        return this.checks.stream().filter(check -> check.getCheckName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
