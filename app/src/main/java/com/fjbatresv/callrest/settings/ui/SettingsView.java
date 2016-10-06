package com.fjbatresv.callrest.settings.ui;

import com.fjbatresv.callrest.entities.Settings;

/**
 * Created by javie on 5/10/2016.
 */
public interface SettingsView {
    void loading(boolean load);
    void loadActual(Settings settings);
    void showError(String error);
    void saved();
}
