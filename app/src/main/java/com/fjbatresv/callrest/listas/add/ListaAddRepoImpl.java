package com.fjbatresv.callrest.listas.add;

import android.content.Context;
import android.util.Log;

import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.entities.Contacto_Table;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.entities.Lista_Table;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.add.events.ListaAddEvent;
import com.fjbatresv.callrest.utils.Crypto;
import com.fjbatresv.callrest.utils.Queries;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

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
    public void saveLista(Lista lista, boolean nuevo) {
        Lista test = Queries.listaNombre(lista.getNombre());
        if (test != null && ((test.getNombre() != null && nuevo) || (!nuevo && !lista.getId().equals(test.getId())))) {
            bus.post(new ListaAddEvent(ListaAddEvent.ADD_LIST, context.getString(R.string.listas_add_error_repetido)));
        } else {
            Log.e("es nuevo", nuevo ? "si" : "no" + nuevo);
            if (nuevo) {
                lista.setId(Crypto.getRandomUuid());
            }else{
                Lista listaA = SQLite.select().from(Lista.class).where(Lista_Table.id.eq(lista.getId())).querySingle();
                SQLite.update(Contacto.class).set(Contacto_Table.nombreLista.eq(lista.getNombre()))
                        .where(Contacto_Table.nombreLista.is(listaA.getNombre())).execute();
            }
            lista.save();
            bus.post(new ListaAddEvent(ListaAddEvent.ADD_LIST, lista));
        }
    }

    @Override
    public void loadList(String nombre) {
        Lista lista = Queries.listaNombre(nombre);
        if (lista != null) {
            bus.post(new ListaAddEvent(ListaAddEvent.LOAD_LIST, lista));
        } else {
            bus.post(new ListaAddEvent(ListaAddEvent.LOAD_LIST,
                    String.format(context.getString(R.string.listas_add_error_lista_not_found),
                            nombre)));
        }
    }
}
