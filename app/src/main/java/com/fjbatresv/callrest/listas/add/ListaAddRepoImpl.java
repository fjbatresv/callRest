package com.fjbatresv.callrest.listas.add;

import android.content.Context;

import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.entities.Lista_Table;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.add.events.ListaAddEvent;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by javie on 29/09/2016.
 */
public class ListaAddRepoImpl implements ListaAddRepo {
    private EventBus bus;
    private Context context;

    public ListaAddRepoImpl(EventBus bus, Context context) {
        this.bus = bus;
        this.context = context;
    }

    @Override
    public void saveLista(Lista lista) {
        Lista test = SQLite.select().from(Lista.class)
                .where(Lista_Table.nombre.eq(lista.getNombre())).querySingle();
        if (test != null && test.getNombre() != null && lista.getNombre().equalsIgnoreCase(lista.getNombre())){
            bus.post(new ListaAddEvent(ListaAddEvent.ADD_LIST, context.getString(R.string.listas_add_error_repetido)));
        }else{
            lista.save();
            bus.post(new ListaAddEvent(ListaAddEvent.ADD_LIST, lista));
        }
    }
}
