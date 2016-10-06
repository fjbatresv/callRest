package com.fjbatresv.callrest.settings;

import com.fjbatresv.callrest.entities.Settings;
import com.fjbatresv.callrest.libs.base.EventBus;

/**
 * Created by javie on 6/10/2016.
 */
public class SettingInteractorImpl implements SettingsInteractor {
    private EventBus bus;
    private SettingsRepo repo;

    public SettingInteractorImpl(EventBus bus, SettingsRepo repo) {
        this.bus = bus;
        this.repo = repo;
    }

    @Override
    public void load() {
        repo.load();
    }

    @Override
    public void save(Settings settings) {
        repo.save(settings);
    }
}
