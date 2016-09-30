package com.fjbatresv.callrest.listas.view;

import android.content.Context;

import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.view.events.ListaViewEvent;
import com.fjbatresv.callrest.utils.Crypto;
import com.fjbatresv.callrest.utils.Queries;

import java.util.ArrayList;
import java.util.List;

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
        if (lista == null) {
            bus.post(new ListaViewEvent(ListaViewEvent.LOAD_LIST,
                    String.format(context.getString(R.string.listas_view_error_nombre), nombre)));
            return;
        }
        bus.post(new ListaViewEvent(ListaViewEvent.LOAD_LIST, lista));
    }

    @Override
    public void addContact(Lista lista, final Contacto contacto) {
        List<Contacto> contactos = new ArrayList<Contacto>() {{
            add(contacto);
        }};
        contacto.setId(Crypto.getRandomUuid());
        if (lista.getContactos() != null) {
            contactos = lista.getContactos();
            contactos.add(contacto);
        }
        lista.setContactos(contactos);
        contacto.save();
        lista.save();
        bus.post(new ListaViewEvent(ListaViewEvent.ADD_CONTACT, lista));
    }

    @Override
    public void removeContact(Lista lista, Contacto contacto) {
        lista.getContactos().remove(contacto);
        contacto.delete();
        lista.save();
        bus.post(new ListaViewEvent(ListaViewEvent.REMOVE_CONTACT, lista));
    }
}
