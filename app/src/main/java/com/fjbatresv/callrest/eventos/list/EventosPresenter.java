package com.fjbatresv.callrest.eventos.list;

import com.fjbatresv.callrest.eventos.list.events.EventosEvents;

/**
 * Created by javie on 5/10/2016.
 */
public interface EventosPresenter {
    void onCreate();
    void onDestroy();

    void onEventMainThread(EventosEvents events);

    void loadList();
}
