package com.fjbatresv.callrest.listas.view.events;

import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.entities.Lista;

/**
 * Created by javie on 29/09/2016.
 */
public class ListaViewEvent {
    public static final int REMOVE_CONTACT = 2;
    public static final int DELETE_LIST = 3;
    private int tipo;
    private String error;
    private Contacto contacto;
    private Lista lista;

    public static final int LOAD_LIST = 0;
    public static final int ADD_CONTACT = 1;

    public ListaViewEvent() {
    }

    public ListaViewEvent(int tipo) {
        this.tipo = tipo;
    }

    public ListaViewEvent(int tipo, String error) {
        this.tipo = tipo;
        this.error = error;
    }

    public ListaViewEvent(int tipo, Contacto contacto) {
        this.tipo = tipo;
        this.contacto = contacto;
    }

    public ListaViewEvent(int tipo, Lista lista) {
        this.tipo = tipo;
        this.lista = lista;
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

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }
}
