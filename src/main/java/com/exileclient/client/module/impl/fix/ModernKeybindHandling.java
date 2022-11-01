package com.exileclient.client.module.impl.fix;

import com.exileclient.client.ExileClient;
import com.exileclient.client.config.GlobalSettings;
import com.exileclient.client.event.impl.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.lang.reflect.Field;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class ModernKeybindHandling {

    private final Minecraft mc;
    private boolean hasScreen;

    public ModernKeybindHandling() {
        ExileClient.getInstance().getEventBus().addEvent(TickEvent.class, this::handleTick);
        this.mc = Minecraft.getMinecraft();
    }

    public void handleTick(TickEvent event) {
        boolean hasScreen = mc.currentScreen != null;
        GlobalSettings globalSettings = ExileClient.getInstance().getGlobalSettings();
        if (!hasScreen && this.hasScreen && globalSettings.modernKeybindHandling.getValue()) {
            Class<?> gameSettingsClass = GameSettings.class;
            Field[] fields = gameSettingsClass.getDeclaredFields();

            // yes I used reflection so what
            for (Field field : fields) {
                if (field.getType().equals(KeyBinding.class)) {
                    field.setAccessible(true);

                    try {
                        KeyBinding keyBinding = (KeyBinding) field.get(mc.gameSettings);
                        boolean excludeSneak = !globalSettings.excludeSneak.getValue() || !keyBinding.getKeyDescription().equalsIgnoreCase("key.sneak");
                        boolean excludeThrow = !globalSettings.excludeThrow.getValue() || !keyBinding.getKeyDescription().equalsIgnoreCase("key.use");
                        if (!keyBinding.getKeyDescription().equalsIgnoreCase("key.inventory") && !keyBinding.getKeyDescription().equalsIgnoreCase("key.chat") && !keyBinding.getKeyDescription().equalsIgnoreCase("key.command") && excludeSneak && excludeThrow && keyBinding.getKeyCode() > 0 && Keyboard.isKeyDown(keyBinding.getKeyCode())) {
                            KeyBinding.setKeyBindState(keyBinding.getKeyCode(), true);
                            KeyBinding.onTick(keyBinding.getKeyCode());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        this.hasScreen = hasScreen;
    }
}
