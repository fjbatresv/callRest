package com.fjbatresv.callrest.listas.list;

import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.list.events.ListasEvent;
import com.fjbatresv.callrest.listas.list.ui.ListasView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by javie on 27/09/2016.
 */
public class ListasPresenterImpl implements ListasPresenter {
    private ListasView view;
    private ListaInteractor interactor;
    private EventBus bus;

    public ListasPresenterImpl(ListasView view, ListaInteractor interactor, EventBus bus) {
        this.view = view;
        this.interactor = interactor;
        this.bus = bus;
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
    public void onEventMainThred(ListasEvent event) {
        view.loading(false);
        if (event.getError() != null && !event.getError().isEmpty()){
            view.showError(event.getError());
        }else{
            switch (event.getTipo()){
                case ListasEvent.GET_LISTS:
                    view.setListas(event.getListas());
                    break;
            }
        }
    }

    @Override
    public void getLists() {
        view.loading(true);
        interactor.getLists();
    }
}
