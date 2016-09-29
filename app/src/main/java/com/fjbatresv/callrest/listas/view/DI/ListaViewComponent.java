package com.fjbatresv.callrest.listas.view.DI;

import com.fjbatresv.callrest.AppModule;
import com.fjbatresv.callrest.libs.DI.LibsModule;
import com.fjbatresv.callrest.listas.view.ui.ListaViewActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 29/09/2016.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class, ListaViewModule.class})
public interface ListaViewComponent {
    void inject(ListaViewActivity activity);
}
