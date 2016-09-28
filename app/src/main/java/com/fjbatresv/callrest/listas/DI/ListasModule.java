package com.fjbatresv.callrest.listas.DI;

import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.ListaInteractor;
import com.fjbatresv.callrest.listas.ListaInteractorImpl;
import com.fjbatresv.callrest.listas.ListasPresenter;
import com.fjbatresv.callrest.listas.ListasPresenterImpl;
import com.fjbatresv.callrest.listas.ListasRepo;
import com.fjbatresv.callrest.listas.ListasRepoImpl;
import com.fjbatresv.callrest.listas.ui.ListasView;
import com.fjbatresv.callrest.listas.ui.adapters.ListasAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 28/09/2016.
 */
@Module
public class ListasModule {
    private ListasView view;

    public ListasModule(ListasView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    ListasView providesListasView(){
        return this.view;
    }

    @Singleton
    @Provides
    ListasPresenter providesListasPresenter(ListasView view, ListaInteractor interactor, EventBus bus){
        return new ListasPresenterImpl(view, interactor, bus);
    }

    @Singleton
    @Provides
    ListaInteractor providesListaInteractor(EventBus bus, ListasRepo repo){
        return new ListaInteractorImpl(bus, repo);
    }

    @Singleton
    @Provides
    ListasRepo providesListasRepo(EventBus bus){
        return new ListasRepoImpl(bus);
    }

    @Singleton
    @Provides
    ListasAdapter providesListasAdapter(List<Lista> listas){
        return new ListasAdapter(listas);
    }

    @Singleton
    @Provides
    List<Lista> providesListas(){
        return new ArrayList<Lista>();
    }
}
