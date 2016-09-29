package com.fjbatresv.callrest.listas.view;

import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.view.events.ListaViewEvent;
import com.fjbatresv.callrest.listas.view.ui.ListaViewView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by javie on 29/09/2016.
 */
public class ListaViewPresenterImpl implements ListaViewPresenter {
    private EventBus bus;
    private ListaViewView view;
    private ListaViewInteractor interactor;

    public ListaViewPresenterImpl(EventBus bus, ListaViewView view, ListaViewInteractor interactor) {
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
    public void onEventMainThread(ListaViewEvent event) {
        view.loading(false);
        if (event.getError() != null && !event.getError().isEmpty()){
            view.showError(event.getError());
        }else{
            switch (event.getTipo()){
                case ListaViewEvent.LOAD_LIST:
                    view.foundedList(event.getLista());
                    break;
            }
        }
    }

    @Override
    public void loadList(String nombre) {
        view.loading(true);
        interactor.loadList(nombre);
    }
}
