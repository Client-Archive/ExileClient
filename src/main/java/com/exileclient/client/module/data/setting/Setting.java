package com.exileclient.client.module.data.setting;

import com.exileclient.client.module.AbstractModule;
import lombok.Getter;

import java.util.List;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 *
 * Setting base.
 */
@Getter
public class Setting {

    private final String name;
    public Object value;

    private AbstractModule container;

    /**
     * The main setting constructor, this can be tweaked later.
     *
     * @param container - Module this is being assigned to.
     * @param name - The setting name.
     */
    public Setting(AbstractModule container, String name) {
        this.container = container;
        this.name = name;

        container.getSettings().add(this);
    }

    /**
     * Added mainly for global settings.
     *
     * @param list
     * @param name
     */
    public Setting(List<Setting> list, String name) {
        list.add(this);
        this.name = name;
    }

    /**
     * Sets the value of the setting.
     *
     * @param value - The new value.
     * @return - Must return the setting itself because we will stack setters on top of each other when creating them in code.
     */
    public Setting setValue(Object value) {
        this.value = value;
        return this;
    }

}