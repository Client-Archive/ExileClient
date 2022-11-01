package com.exileclient.client.module.data.setting.types;

import com.exileclient.client.module.AbstractModule;
import com.exileclient.client.module.data.setting.Setting;

import java.util.List;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class TextFieldSetting extends Setting {

    public TextFieldSetting(AbstractModule container, String name) {
        super(container, name);
    }

    public TextFieldSetting(List<Setting> list, String name) {
        super(list, name);
    }

    public String getValue() {
        return (String) this.value;
    }

}
