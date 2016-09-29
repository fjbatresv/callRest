package com.fjbatresv.callrest.listas.list;

import com.fjbatresv.callrest.libs.base.EventBus;

/**
 * Created by javie on 27/09/2016.
 */
public class ListaInteractorImpl implements ListaInteractor {
    private EventBus bus;
    private ListasRepo repo;

    public ListaInteractorImpl(EventBus bus, ListasRepo repo) {
        this.bus = bus;
        this.repo = repo;
    }

    @Override
    public void getLists() {
        repo.getLists();
    }
}
