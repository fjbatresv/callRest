package com.fjbatresv.callrest.listas.add;

import android.content.Context;

import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.add.events.ListaAddEvent;

/**
 * Created by javie on 29/09/2016.
 */
public class ListaAddInteractorImpl implements ListaAddInteractor {
    private EventBus bus;
    private ListaAddRepo repo;
    private Context context;

    public ListaAddInteractorImpl(EventBus bus, ListaAddRepo repo, Context context) {
        this.bus = bus;
        this.repo = repo;
        this.context = context;
    }

    @Override
    public void saveList(Lista lista, boolean nuevo) {
        if (lista.getNombre() == null || lista.getNombre().isEmpty()){
            bus.post(new ListaAddEvent(ListaAddEvent.ADD_LIST, context.getString(R.string.listas_add_error_nombre)));
            return;
        }
        repo.saveLista(lista, nuevo);
    }

    @Override
    public void loadList(String nombre) {
        if (nombre == null || nombre.isEmpty()){
            bus.post(new ListaAddEvent(ListaAddEvent.LOAD_LIST, context.getString(R.string.listas_add_error_not_defined)));
            return;
        }
        repo.loadList(nombre);
    }
}
