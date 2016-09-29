package com.fjbatresv.callrest.listas.view;

import com.fjbatresv.callrest.listas.view.events.ListaViewEvent;

/**
 * Created by javie on 29/09/2016.
 */
public interface ListaViewPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(ListaViewEvent event);
    void loadList(String nombre);
}
