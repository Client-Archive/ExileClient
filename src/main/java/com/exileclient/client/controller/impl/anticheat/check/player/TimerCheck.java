package com.exileclient.client.controller.impl.anticheat.check.player;

import com.exileclient.client.controller.impl.anticheat.AntiCheat;
import com.exileclient.client.controller.impl.anticheat.check.CheckCategory;

import javax.swing.*;
import java.io.IOException;

public class TimerCheck extends AntiCheat {

    private float violations = 0;

    public TimerCheck() {
        super("Timer", CheckCategory.PLAYER, 10);
    }

    @Override
    public void check() {
        if (mc.timer.timerSpeed > 1.025D && mc.thePlayer.ticksExisted > 20) {
            violations += 1;
            System.out.println(mc.thePlayer.getName() + " has flagged " + getCheckName() + " | VL: " + violations + "/" + maxViolations);
        }
    }

    @Override
    public void checkViolations() {
        if(violations >= maxViolations) {
            acmsg.setContent(mc.thePlayer.getName() + " might be using " + checkName + " | VL: " + violations + "/" + maxViolations);
            try {
                acmsg.execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JOptionPane.showMessageDialog(null, "Your game has been closed. Reason: Unfair Advantage.");
            mc.shutdown();
        }
    }
}
