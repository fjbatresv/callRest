package com.fjbatresv.callrest.settings;

import com.fjbatresv.callrest.entities.Settings;
import com.fjbatresv.callrest.settings.events.SettingsEvent;

/**
 * Created by javie on 5/10/2016.
 */
public interface SettingsPresenter {
    void onCreate();
    void onDestroy();

    void onEventMainThread(SettingsEvent event);

    void load();
    void save(Settings settings);
}
