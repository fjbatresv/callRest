package com.fjbatresv.callrest.listas.add;

import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.add.events.ListaAddEvent;
import com.fjbatresv.callrest.listas.add.ui.ListaAddView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by javie on 29/09/2016.
 */
public class ListaAddPresenterImpl implements ListaAddPresenter {
    private EventBus bus;
    private ListaAddView view;
    private ListaAddInteractor interactor;

    public ListaAddPresenterImpl(EventBus bus, ListaAddView view, ListaAddInteractor interactor) {
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
    public void onEventMainThread(ListaAddEvent event) {
        view.loading(false);
        if (event.getError() != null && !event.getError().isEmpty()){
            view.showError(event.getError());
        }else{
            switch (event.getTipo()){
                case ListaAddEvent.ADD_LIST:
                    view.listaDone(event.getLista());
                    break;
            }
        }
    }

    @Override
    public void saveLista(Lista lista) {
        view.loading(true);
        interactor.saveList(lista);
    }
}
