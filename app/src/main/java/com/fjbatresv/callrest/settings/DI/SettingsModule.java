package com.fjbatresv.callrest.settings.DI;

import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.settings.SettingInteractorImpl;
import com.fjbatresv.callrest.settings.SettingsInteractor;
import com.fjbatresv.callrest.settings.SettingsPresenter;
import com.fjbatresv.callrest.settings.SettingsPresenterImpl;
import com.fjbatresv.callrest.settings.SettingsRepo;
import com.fjbatresv.callrest.settings.SettingsRepoImpl;
import com.fjbatresv.callrest.settings.ui.SettingsView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 6/10/2016.
 */
@Module
public class SettingsModule {
    private SettingsView view;

    public SettingsModule(SettingsView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    SettingsView providesSettingsView(){
        return this.view;
    }

    @Singleton
    @Provides
    SettingsPresenter providesSettingsPresenter(EventBus bus, SettingsView view, SettingsInteractor interactor){
        return new SettingsPresenterImpl(bus, view, interactor);
    }

    @Singleton
    @Provides
    SettingsInteractor providesSettingsInteractor(EventBus bus, SettingsRepo repo){
        return new SettingInteractorImpl(bus, repo);
    }

    @Singleton
    @Provides
    SettingsRepo providesSettingsRepo(EventBus bus){
        return new SettingsRepoImpl(bus);
    }
}
