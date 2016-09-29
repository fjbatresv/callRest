package com.fjbatresv.callrest.listas.add;

import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.listas.add.events.ListaAddEvent;

/**
 * Created by javie on 29/09/2016.
 */
public interface ListaAddPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(ListaAddEvent event);

    void saveLista(Lista lista);
}
