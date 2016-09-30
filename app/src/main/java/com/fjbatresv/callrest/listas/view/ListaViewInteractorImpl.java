package com.fjbatresv.callrest.listas.view;

import android.content.Context;

import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.view.events.ListaViewEvent;

import org.w3c.dom.ProcessingInstruction;

/**
 * Created by javie on 29/09/2016.
 */
public class ListaViewInteractorImpl implements ListaViewInteractor {
    private EventBus bus;
    private ListaViewRepo repo;
    private Context context;

    public ListaViewInteractorImpl(EventBus bus, ListaViewRepo repo, Context context) {
        this.bus = bus;
        this.repo = repo;
        this.context = context;
    }

    @Override
    public void loadList(String nombre) {
        if (nombre == null){
            bus.post(new ListaViewEvent(ListaViewEvent.LOAD_LIST, context.getString(R.string.listas_view_error_nombre)));
            return;
        }else{
            repo.loadList(nombre);
        }
    }

    @Override
    public void addContact(Lista lista, Contacto contacto) {
        repo.addContact(lista, contacto);
    }

    @Override
    public void removeContact(Lista lista, Contacto contacto) {
        repo.removeContact(lista, contacto);
    }
}
