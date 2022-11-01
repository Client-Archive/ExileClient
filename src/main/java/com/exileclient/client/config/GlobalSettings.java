package com.exileclient.client.config;

import com.exileclient.client.module.data.setting.Setting;
import com.exileclient.client.module.data.setting.types.BooleanSetting;
import com.exileclient.client.module.data.setting.types.ChoiceSetting;
import com.exileclient.client.module.data.setting.types.LabelSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class GlobalSettings {

    public static boolean debug = false;

    public List<Setting> settingsList = new ArrayList<>();

    public LabelSetting renderSettings;
    public LabelSetting packMenuSettings;

    public BooleanSetting transparentBackground;
    public ChoiceSetting clearGlass;
    public BooleanSetting redString;
    // TODO: color setting for a custom red string color

    // modern keybind handling
    public BooleanSetting modernKeybindHandling;
    public BooleanSetting excludeSneak;
    public BooleanSetting excludeThrow;

    // pack menu settings
    public BooleanSetting widePackMenu;
    public BooleanSetting showPackFolderInfo;
    public BooleanSetting showPackFolderIcons;
    public BooleanSetting showPackIcons;
    public BooleanSetting showPackDescriptions;
    public BooleanSetting showPackSearchBar;
    public ChoiceSetting packSortMethod;

    public GlobalSettings() {

        this.renderSettings = new LabelSetting(this.settingsList, "Render Settings");
        this.clearGlass = new ChoiceSetting(this.settingsList, "Clear Glass")
                .setValue("OFF")
                .setAcceptedStringValues("OFF", "NORMAL", "ALL");

        this.redString = new BooleanSetting(this.settingsList, "Colored/Red String")
                .setValue(false);

        this.transparentBackground = new BooleanSetting(this.settingsList, "Transparent Background")
                .setValue(true);

        this.modernKeybindHandling = new BooleanSetting(this.settingsList, "Modern Keybind Handling")
                .setValue(true);

        this.excludeSneak = new BooleanSetting(this.settingsList, "Exclude Sneaking")
                .setValue(false);

        this.excludeThrow = new BooleanSetting(this.settingsList, "Exclude Throwing")
                .setValue(false);


        this.packMenuSettings = new LabelSetting(this.settingsList, "Pack Menu Settings");
        this.widePackMenu = new BooleanSetting(this.settingsList, "Wide Pack Menu")
                .setValue(true);

        this.showPackFolderInfo = new BooleanSetting(this.settingsList, "Show Pack Folder Info")
                .setValue(true);

        this.showPackFolderIcons = new BooleanSetting(this.settingsList, "Show Pack Folder Icons")
                .setValue(true);

        this.showPackIcons = new BooleanSetting(this.settingsList, "Show Pack Icons")
                .setValue(true);

        this.showPackDescriptions = new BooleanSetting(this.settingsList, "Show Descriptions")
                .setValue(true);

        this.showPackSearchBar = new BooleanSetting(this.settingsList, "Show Search Bar")
                .setValue(true);

        this.packSortMethod = new ChoiceSetting(this.settingsList, "Sorting Method")
                .setAcceptedStringValues("A-Z", "Z-A")
                .setValue("A-Z");

    }


}
