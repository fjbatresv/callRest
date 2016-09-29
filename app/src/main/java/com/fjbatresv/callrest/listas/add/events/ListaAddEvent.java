package com.fjbatresv.callrest.listas.add.events;

import com.fjbatresv.callrest.entities.Lista;

/**
 * Created by javie on 29/09/2016.
 */
public class ListaAddEvent {
    private int tipo;
    private String error;
    private Lista lista;

    public static final int ADD_LIST = 0;

    public ListaAddEvent() {
    }

    public ListaAddEvent(int tipo, String error) {
        this.tipo = tipo;
        this.error = error;
    }

    public ListaAddEvent(int tipo, Lista lista) {
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

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }
}
