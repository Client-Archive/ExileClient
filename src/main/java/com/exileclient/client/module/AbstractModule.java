package com.exileclient.client.module;

import com.exileclient.client.ExileClient;
import com.exileclient.client.event.EventBus;
import com.exileclient.client.module.data.setting.Setting;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 *
 * Defines what the fuck a mod is
 */
@Getter
public abstract class AbstractModule {

    protected Minecraft mc = Minecraft.getMinecraft();

    private final Map<Class<? extends EventBus.Event>, Consumer> events = new HashMap<>();

    private final List<Setting> settings;

    private final String name;
    private String description;
    private String[] authors;

    private boolean defaultState = false;
    private boolean state = false;

    private float x = 0F;
    private float y = 0F;

    public float defaultX = 0F;
    public float defaultY = 0F;

    public float width = 0F;
    public float height = 0F;

    /**
     * Basic mod with no authors. Unlikely to be used.
     *
     * @param name - The module name that's displayed in the mod menu.
     * @param description - A brief of description of the module that would be shown in the mod menu settings.
     *
     */
    public AbstractModule(String name, String description) {
        this.name = name;
        this.description = description;

        this.settings = new ArrayList<>();
    }

    /**
     * The main module constructor that will be used.
     *
     * @param name - The name of the mod. (Example: FPS)
     * @param description - A brief of description of the mod. (Example: Shows the FPS you are getting in-game.)
     * @param authors - All mod authors, no matter if we used the original code, we are crediting them because it's the right thing to do.
     */
    public AbstractModule(String name, String description, String[] authors) {
        this.name = name;
        this.description = description;
        this.authors = authors;

        this.settings = new ArrayList<>();
    }

    /**
     * Sets the mod state, this will be used in the mod menu.
     *
     * @param state - The mod state.
     */
    public void setState(boolean state) {

        if (state) {
            if (!this.state) {
                this.state = true;
                this.addAllEvents();
            }
        } else if (this.state) {
            this.state = false;
            this.removeAllEvents();
        }

    }

    /**
     * Sets teh default mod state.
     *
     * @param state - The mod state.
     */
    public void setDefaultState(boolean state) {

        if (state) {
            if (!this.state) {
                this.state = true;
                this.addAllEvents();
            }
        } else if (this.state) {
            this.state = false;
            this.removeAllEvents();
        }

        this.defaultState = this.state;
    }

    /**
     * Sets the position of the mod.
     *
     * @param x - x pos
     * @param y - y pos
     */
    public void setPositions(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the default position of the mod, will be used for first time setup likely.
     *
     * @param x - default x pos
     * @param y - default y pos
     */
    public void setDefaultPositions(float x, float y) {
        this.x = x;
        this.y = y;
        this.defaultX = x;
        this.defaultY = y;
    }

    /**
     * Sets the dimensions of the module bounding box.
     *
     * @param width - width of the bounding box.
     * @param height - height of the bounding box.
     */
    public void setDimensions(float width, float height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Scales the mod.
     *
     * @param scaledResolution
     */
    public void scale(ScaledResolution scaledResolution) {
        float f3 = 2.0f;
        float f4 = 2.0f;
        float scale = this.getScale();
        GL11.glScalef(scale, scale, scale);

        GL11.glTranslatef(f3 / scale, f4 / scale, 0.0f);
        GL11.glTranslatef(this.x / scale, this.y / scale, 0.0f);
    }

    /**
     * Used to add an event to a certain mod, this should be used in all hud mods for rendering.
     *
     * @param eventClass
     * @param consumer
     * @param <T>
     */
    protected <T extends EventBus.Event> void addEvent(Class<T> eventClass, Consumer<T> consumer) {
        this.events.put(eventClass, consumer);
    }

    /**
     * Adds all events, called when mod is enabled.
     */
    protected void addAllEvents() {
        for (Map.Entry<Class<? extends EventBus.Event>, Consumer> entry : this.events.entrySet()) {
            ExileClient.getInstance().getEventBus().addEvent(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Removes all events, called when mod is disabled.
     */
    protected void removeAllEvents() {
        for (Map.Entry<Class<? extends EventBus.Event>, Consumer> entry : this.events.entrySet()) {
            ExileClient.getInstance().getEventBus().removeEvent(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Returns the scale factor for module scaling.
     */
    private float getScale() {
        ScaledResolution sacledRes = new ScaledResolution(Minecraft.getMinecraft());
        return sacledRes.getScaleFactor() / 2.0F;
    }
}
