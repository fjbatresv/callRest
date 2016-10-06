package com.fjbatresv.callrest.settings.events;

import com.fjbatresv.callrest.entities.Settings;

/**
 * Created by javie on 5/10/2016.
 */
public class SettingsEvent {
    public static final int LOAD = 0;
    public static final int SAVE = 1;
    private int tipo;
    private String error;
    private Settings settings;

    public SettingsEvent() {
    }

    public SettingsEvent(int tipo, String error) {
        this.tipo = tipo;
        this.error = error;
    }

    public SettingsEvent(int tipo) {
        this.tipo = tipo;
    }

    public SettingsEvent(int tipo, Settings settings) {
        this.tipo = tipo;
        this.settings = settings;
    }

    public SettingsEvent(int tipo, String error, Settings settings) {
        this.tipo = tipo;
        this.error = error;
        this.settings = settings;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
