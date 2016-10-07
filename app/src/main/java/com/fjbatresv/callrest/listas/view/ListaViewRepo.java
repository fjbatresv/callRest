package com.fjbatresv.callrest.listas.view;

import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.entities.Lista;

/**
 * Created by javie on 29/09/2016.
 */
public interface ListaViewRepo {
    void loadList(String nombre);

    void addContact(Lista lista, Contacto contacto);

    void removeContact(Lista lista, Contacto contacto);

    void delete(String id);
}
