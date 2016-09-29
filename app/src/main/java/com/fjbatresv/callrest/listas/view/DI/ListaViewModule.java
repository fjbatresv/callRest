package com.fjbatresv.callrest.listas.view.DI;

import android.content.Context;

import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.view.ListaViewInteractor;
import com.fjbatresv.callrest.listas.view.ListaViewInteractorImpl;
import com.fjbatresv.callrest.listas.view.ListaViewPresenter;
import com.fjbatresv.callrest.listas.view.ListaViewPresenterImpl;
import com.fjbatresv.callrest.listas.view.ListaViewRepo;
import com.fjbatresv.callrest.listas.view.ListaViewRepoImpl;
import com.fjbatresv.callrest.listas.view.ui.ListaViewView;
import com.fjbatresv.callrest.listas.view.ui.adapters.ListaViewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 29/09/2016.
 */
@Module
public class ListaViewModule {
    private ListaViewView view;

    public ListaViewModule(ListaViewView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    ListaViewView providesListaViewView(){
        return this.view;
    }

    @Singleton
    @Provides
    ListaViewPresenter providesListaViewPresenter(EventBus bus, ListaViewView view, ListaViewInteractor interactor){
        return new ListaViewPresenterImpl(bus, view, interactor);
    }

    @Singleton
    @Provides
    ListaViewInteractor providesListaViewInteractor(ListaViewRepo repo, EventBus bus, Context context){
        return new ListaViewInteractorImpl(bus, repo, context);
    }

    @Singleton
    @Provides
    ListaViewRepo providesListaViewRepo(EventBus bus, Context context){
        return new ListaViewRepoImpl(bus, context);
    }

    @Singleton
    @Provides
    ListaViewAdapter providesListaViewAdapter(List<Contacto> contactos){
        return new ListaViewAdapter(contactos);
    }

    @Singleton
    @Provides
    List<Contacto> providesContactos(){
        return new ArrayList<Contacto>();
    }
}
