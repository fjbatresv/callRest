package com.fjbatresv.callrest.settings;

import com.fjbatresv.callrest.entities.Settings;

/**
 * Created by javie on 5/10/2016.
 */
public interface SettingsInteractor {
    void load();

    void save(Settings settings);
}
