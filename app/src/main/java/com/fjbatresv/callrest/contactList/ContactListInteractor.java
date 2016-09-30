package com.fjbatresv.callrest.contactList;

import com.fjbatresv.callrest.entities.Contacto;

import java.util.List;

/**
 * Created by javie on 29/09/2016.
 */
public interface ContactListInteractor {
    void loadContacts(String lista);
    void saveContacts(List<Contacto> contactos, String nombre);
}
