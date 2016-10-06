package com.fjbatresv.callrest.settings;

import com.fjbatresv.callrest.entities.Settings;
import com.fjbatresv.callrest.entities.Settings_Table;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.settings.events.SettingsEvent;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by javie on 6/10/2016.
 */
public class SettingsRepoImpl implements SettingsRepo {
    private EventBus bus;

    public SettingsRepoImpl(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void load() {
        Settings settings = SQLite.select().from(Settings.class).where(Settings_Table.id.eq(1)).querySingle();
        bus.post(new SettingsEvent(SettingsEvent.LOAD, settings));
    }

    @Override
    public void save(Settings settings) {
        settings.save();
        bus.post(new SettingsEvent(SettingsEvent.SAVE));
    }
}
