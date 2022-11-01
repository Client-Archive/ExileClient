package com.exileclient.client.ui.gui.pack;

import com.exileclient.client.ExileClient;
import com.exileclient.client.event.data.Subscriber;
import com.exileclient.client.event.impl.InitializationEvent;
import com.exileclient.client.ui.gui.pack.utils.PackUtils;
import com.exileclient.client.ui.gui.pack.utils.ThreadUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.ResourcePackRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class ResourcePackManager {

    private final Minecraft mc = Minecraft.getMinecraft();

    public ResourcePackManager() {
        ExileClient.getInstance().getEventBus().addEvent(InitializationEvent.class, this::onInit);
    }

    @Subscriber
    public void onInit(InitializationEvent event) {
        HashMap<String, ResourcePackRepository.Entry> activeEntries = new HashMap<>();
        for (String packName : this.mc.gameSettings.resourcePacks) {
            activeEntries.put(packName, null);
        }

        for (ResourcePackRepository.Entry entry : this.mc.getResourcePackRepository().getRepositoryEntries()) {
            activeEntries.put(entry.getResourcePackName(), entry);
        }

        for (ResourcePackRepository.Entry entry : PackUtils.getActiveEntries()) {
            activeEntries.put(entry.getResourcePackName(), entry);
        }

        activeEntries.values().removeIf(Objects::isNull);
        this.mc.getResourcePackRepository().setRepositories(new ArrayList<>(activeEntries.values()));
        this.mc.refreshResources();
        Runtime.getRuntime().addShutdownHook(new Thread(ThreadUtils::shutdown));
    }

}
