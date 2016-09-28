package com.fjbatresv.callrest.listas;

import com.fjbatresv.callrest.listas.events.ListasEvent;

/**
 * Created by javie on 27/09/2016.
 */
public interface ListasPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThred(ListasEvent event);
    void getLists();
}
