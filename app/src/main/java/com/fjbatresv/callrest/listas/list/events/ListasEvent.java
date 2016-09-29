package com.fjbatresv.callrest.listas.list.events;

import com.fjbatresv.callrest.entities.Lista;

import java.util.List;

/**
 * Created by javie on 27/09/2016.
 */
public class ListasEvent {
    public static final int GET_LISTS = 0;
    private int tipo;
    private String error;
    private List<Lista> listas;

    public ListasEvent() {
    }

    public ListasEvent(int tipo, String error) {
        this.tipo = tipo;
        this.error = error;
    }

    public ListasEvent(int tipo) {
        this.tipo = tipo;
    }

    public ListasEvent(int tipo, List<Lista> listas) {
        this.tipo = tipo;
        this.listas = listas;
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

    public List<Lista> getListas() {
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }
}
