package com.fjbatresv.callrest.eventos.list.events;

import com.fjbatresv.callrest.entities.Evento;

import java.util.List;

/**
 * Created by javie on 5/10/2016.
 */
public class EventosEvents {
    private int tipo;
    private String error;
    private List<Evento> eventos;

    public static final int LOAD_EVENTS = 0;

    public EventosEvents() {
    }

    public EventosEvents(int tipo) {
        this.tipo = tipo;
    }

    public EventosEvents(int tipo, String error) {
        this.tipo = tipo;
        this.error = error;
    }

    public EventosEvents(int tipo, List<Evento> eventos) {
        this.tipo = tipo;
        this.eventos = eventos;
    }
}
