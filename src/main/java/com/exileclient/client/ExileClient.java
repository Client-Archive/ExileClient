package com.exileclient.client;

import com.exileclient.client.controller.impl.resource.ResourceController;
import com.exileclient.client.controller.impl.socket.SocketController;
import com.exileclient.client.util.discord.DiscordRP;
import com.exileclient.client.util.layout.gui.hud.HUDManager;
import com.exileclient.client.config.GlobalSettings;
import com.exileclient.client.controller.impl.anticheat.AntiCheat;
import com.exileclient.client.controller.impl.anticheat.AntiCheatManager;
import com.exileclient.client.event.EventBus;
import com.exileclient.client.event.impl.InitializationEvent;
import com.exileclient.client.event.impl.TickEvent;
import com.exileclient.client.ui.gui.pack.ResourcePackManager;
import com.exileclient.client.module.ModuleManager;
import com.exileclient.client.network.websocket.SocketClient;
import lombok.Getter;
import net.minecraft.client.Minecraft;

import javax.swing.*;
import java.util.ArrayList;
import java.util.UUID;

@Getter
public class ExileClient {

    @Getter
    private static ExileClient instance;
    private final Minecraft mc = Minecraft.getMinecraft();

    private final EventBus eventBus;
    private final GlobalSettings globalSettings;
    private final ModuleManager moduleManager;

    private final ResourceController resourceController;
    private final SocketController socketController;

    public final AntiCheat antiCheat;
    public final AntiCheatManager antiCheatManager;

    private final DiscordRP discordRP;
    private final HUDManager hudManager;

    public ExileClient() {
        instance = this;

        if (!SocketClient.isOnline()) {
            JOptionPane.showMessageDialog(null, "Your game has been closed.\nThe client can not start because the Socket went down, or Exile is outdated.");
            Minecraft.getMinecraft().shutdown();
        }

        this.eventBus = new EventBus();
        this.mc.exileLoadingScreen.updatePhase("Events");
        this.moduleManager = new ModuleManager();
        new ResourcePackManager();

        this.mc.exileLoadingScreen.updatePhase("Mods");

        this.globalSettings = new GlobalSettings();
        this.mc.exileLoadingScreen.updatePhase("Global Settings");

        (this.resourceController = new ResourceController()).onLoad();
        this.socketController = new SocketController();

        (discordRP = new DiscordRP()).start();

        this.hudManager = HUDManager.getInstance();
        this.antiCheat = new AntiCheat();
        this.antiCheatManager = new AntiCheatManager();

        this.eventBus.handleEvent(new InitializationEvent());
    }

    /* The client's shutdown method. (Hooked in Minecraft.java @ line 1037) */
    public void handleShutdown() {
        discordRP.shutdown();
    }

}
