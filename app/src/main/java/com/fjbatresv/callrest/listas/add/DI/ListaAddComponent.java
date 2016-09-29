package com.fjbatresv.callrest.listas.add.DI;

import com.fjbatresv.callrest.AppModule;
import com.fjbatresv.callrest.libs.DI.LibsModule;
import com.fjbatresv.callrest.listas.add.ui.ListaAddActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 29/09/2016.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class, ListaAddModule.class})
public interface ListaAddComponent {
    void inject(ListaAddActivity addActivity);
}
