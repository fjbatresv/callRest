package com.fjbatresv.callrest.settings;

import com.fjbatresv.callrest.entities.Settings;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.settings.events.SettingsEvent;
import com.fjbatresv.callrest.settings.ui.SettingsView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by javie on 5/10/2016.
 */
public class SettingsPresenterImpl implements SettingsPresenter{
    private EventBus bus;
    private SettingsView view;
    private SettingsInteractor interactor;

    public SettingsPresenterImpl(EventBus bus, SettingsView view, SettingsInteractor interactor) {
        this.bus = bus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        bus.unRegister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(SettingsEvent event) {
        view.loading(false);
        if (event.getError() != null && !event.getError().isEmpty()){
            view.showError(event.getError());
        }else{
            switch (event.getTipo()){
                case SettingsEvent.LOAD:
                    view.loadActual(event.getSettings());
                    break;
                case SettingsEvent.SAVE:
                    view.saved();
                    break;
            }
        }
    }

    @Override
    public void load() {
        view.loading(true);
        interactor.load();
    }

    @Override
    public void save(Settings settings) {
        view.loading(true);
        interactor.save(settings);
    }
}
