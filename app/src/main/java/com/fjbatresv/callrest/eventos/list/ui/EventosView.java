package com.fjbatresv.callrest.eventos.list.ui;

import com.fjbatresv.callrest.entities.Evento;

import java.util.List;

/**
 * Created by javie on 5/10/2016.
 */
public interface EventosView {
    void loading(boolean load);
    void loadEventos(List<Evento> eventos);
    void showError(String error);
}
