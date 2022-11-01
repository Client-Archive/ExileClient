package com.exileclient.client.controller.impl.anticheat;

import com.exileclient.client.controller.impl.anticheat.check.CheckCategory;
import com.exileclient.client.util.discord.DiscordWebhook;
import net.minecraft.client.Minecraft;

public class AntiCheat {

    public static DiscordWebhook acmsg = new DiscordWebhook("https://canary.discord.com/api/webhooks/1006897306659999804/7dzdmlYxmq095NvBpjveIoim_S3QZeRx6KBN_v3flQwsZheRCYZtqsRvDTjjEheCcoDF");
    public static Minecraft mc = Minecraft.getMinecraft();

    private CheckCategory checkCategory;
    public String checkName;
    public float maxViolations;

    public AntiCheat(String checkName, CheckCategory checkCategory, float maxViolations) { // let me think of the best way to actually do this
        this.checkName = checkName;
        this.checkCategory = checkCategory;
        this.maxViolations = maxViolations;

        check();
        checkViolations();
    }
    public AntiCheat() {

    }

    public void check() {

    }

    public void checkViolations() {

    }

    public String getCheckName() {
        return checkName;
    }

    public CheckCategory getCheckCategory() {
        return checkCategory;
    }
}
