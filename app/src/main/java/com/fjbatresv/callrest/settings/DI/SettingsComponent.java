package com.fjbatresv.callrest.settings.DI;

import com.fjbatresv.callrest.AppModule;
import com.fjbatresv.callrest.libs.DI.LibsModule;
import com.fjbatresv.callrest.settings.ui.SettingsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 6/10/2016.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class, SettingsModule.class})
public interface SettingsComponent {
    void inject(SettingsActivity activity);
}
