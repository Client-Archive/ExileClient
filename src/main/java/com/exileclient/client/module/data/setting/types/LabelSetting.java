package com.exileclient.client.module.data.setting.types;

import com.exileclient.client.module.AbstractModule;
import com.exileclient.client.module.data.setting.Setting;

import java.util.List;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class LabelSetting extends Setting {

    public LabelSetting(AbstractModule container, String name) {
        super(container, name);
    }

    public LabelSetting(List<Setting> list, String name) {
        super(list, name);
    }

}
