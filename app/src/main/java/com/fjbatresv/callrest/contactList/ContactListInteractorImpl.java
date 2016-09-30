package com.fjbatresv.callrest.contactList;

import android.content.Context;

import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.libs.base.EventBus;

import java.util.List;

/**
 * Created by javie on 29/09/2016.
 */
public class ContactListInteractorImpl implements ContactListInteractor {
    private EventBus bus;
    private ContactListRepo repo;
    private Context context;

    public ContactListInteractorImpl(EventBus bus, ContactListRepo repo, Context context) {
        this.bus = bus;
        this.repo = repo;
        this.context = context;
    }

    @Override
    public void loadContacts(String lista) {
        repo.loadContacts(lista);
    }

    @Override
    public void saveContacts(List<Contacto> contactos, String nombre) {
        repo.saveContacts(contactos, nombre);
    }
}
