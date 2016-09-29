package com.fjbatresv.callrest.listas.list.DI;

import com.fjbatresv.callrest.AppModule;
import com.fjbatresv.callrest.libs.DI.LibsModule;
import com.fjbatresv.callrest.listas.list.ui.ListasActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 28/09/2016.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class, ListasModule.class})
public interface ListasComponent {
    void inject(ListasActivity activity);
}
