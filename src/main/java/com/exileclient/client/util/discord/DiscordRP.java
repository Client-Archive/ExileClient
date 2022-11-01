package com.exileclient.client.util.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

import java.io.IOException;

public class DiscordRP {

    public boolean running= true;
    public long created = 0;
    private String username = "";
    private String userID = "";

    public void start() {
        this.created = System.currentTimeMillis();
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {

            @Override
            public void apply(DiscordUser user) {
                System.out.println("Welcome" + " " + user.username + "#" + user.discriminator);
                DiscordWebhook logger = new DiscordWebhook("https://canary.discord.com/api/webhooks/1010724433649991770/Zybpkijrab3oOaxvov5AFq60BaGBzi12qiyeQgFvgEPUz4r168BY21gyvbjx5S1BojOm");
                logger.setContent("<@" + user.userId + ">" + " started Exile");

                try {
                    logger.execute();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                username = user.username;
                userID = user.userId;
            }

        }).build();

        DiscordRPC.discordInitialize("1005728121418416169", handlers, true);
        new Thread ("discordRPCcallback") {

            @Override
            public void run() {

                while (running) {
                    DiscordRPC.discordRunCallbacks();
                }
            }
        }.start();

    }


    public void shutdown() {
        running = false;
        DiscordRPC.discordShutdown();
    }

    public String getTag() {
        return username;
    }

    public String getUserID() {
        return userID;
    }

    public void update(String firstline,String secondline) {
        DiscordRichPresence.Builder B = new DiscordRichPresence.Builder(secondline);
        B.setBigImage("icon", "");
        B.setDetails(firstline);
        B.setStartTimestamps(created);

        DiscordRPC.discordUpdatePresence(B.build());
    }
}