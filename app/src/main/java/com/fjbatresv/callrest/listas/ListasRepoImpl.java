package com.fjbatresv.callrest.listas;

import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.events.ListasEvent;
import com.fjbatresv.callrest.utils.Queries;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by javie on 27/09/2016.
 */
public class ListasRepoImpl implements ListasRepo {
    private EventBus bus;

    public ListasRepoImpl(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void getLists() {
        bus.post(new ListasEvent(ListasEvent.GET_LISTS, Queries.listasConHijos()));
    }
}
