package com.fjbatresv.callrest.contactList.events;

import com.fjbatresv.callrest.entities.Contacto;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by javie on 29/09/2016.
 */
public class ContactListEvent {
    private int tipo;
    private String error;
    private List<Contacto> contactos;

    public static final int LOAD_CONTACTS = 0;
    public static final int ADD_CONTACTS = 1;

    public ContactListEvent() {
    }

    public ContactListEvent(int tipo, String error) {
        this.tipo = tipo;
        this.error = error;
    }

    public ContactListEvent(int tipo) {
        this.tipo = tipo;
    }

    public ContactListEvent(int tipo, List<Contacto> contactos) {
        this.tipo = tipo;
        this.contactos = contactos;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }
}
