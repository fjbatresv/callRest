package com.fjbatresv.callrest.listas.view.ui;

import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.entities.Lista;

/**
 * Created by javie on 29/09/2016.
 */
public interface ListaViewView {
    void loading(boolean load);
    void showError(String error);
    void contacAdded(Contacto contacto);
    void foundedList(Lista lista);
}
