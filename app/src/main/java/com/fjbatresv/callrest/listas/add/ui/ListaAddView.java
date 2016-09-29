package com.fjbatresv.callrest.listas.add.ui;

import com.fjbatresv.callrest.entities.Lista;

/**
 * Created by javie on 29/09/2016.
 */
public interface ListaAddView {
    void loading(boolean load);
    void showError(String error);
    void listaDone(Lista lista);
}
