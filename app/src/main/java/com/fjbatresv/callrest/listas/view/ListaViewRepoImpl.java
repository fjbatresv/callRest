package com.fjbatresv.callrest.listas.view;

import android.content.Context;

import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.view.events.ListaViewEvent;
import com.fjbatresv.callrest.utils.Queries;

/**
 * Created by javie on 29/09/2016.
 */
public class ListaViewRepoImpl implements ListaViewRepo {
    private EventBus bus;
    private Context context;

    public ListaViewRepoImpl(EventBus bus, Context context) {
        this.bus = bus;
        this.context = context;
    }

    @Override
    public void loadList(String nombre) {
        Lista lista = Queries.listaHijosNombre(nombre);
        if (lista == null){
            bus.post(new ListaViewEvent(ListaViewEvent.LOAD_LIST,
                    String.format(context.getString(R.string.listas_view_error_nombre), nombre)));
            return;
        }
        bus.post(new ListaViewEvent(ListaViewEvent.LOAD_LIST, lista));
    }
}
