package com.fjbatresv.callrest.listas.list.DI;

import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.list.ListaInteractor;
import com.fjbatresv.callrest.listas.list.ListaInteractorImpl;
import com.fjbatresv.callrest.listas.list.ListasPresenter;
import com.fjbatresv.callrest.listas.list.ListasPresenterImpl;
import com.fjbatresv.callrest.listas.list.ListasRepo;
import com.fjbatresv.callrest.listas.list.ListasRepoImpl;
import com.fjbatresv.callrest.listas.list.ui.ListasView;
import com.fjbatresv.callrest.listas.list.ui.adapters.ListasAdapter;
import com.fjbatresv.callrest.listas.list.ui.adapters.OnItemClickListener;

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
    private OnItemClickListener listener;

    public ListasModule(ListasView view, OnItemClickListener listener) {
        this.view = view;
        this.listener  = listener;
    }

    @Singleton
    @Provides
    ListasView providesListasView(){
        return this.view;
    }

    @Singleton
    @Provides
    OnItemClickListener providesOnItemClickListener(){
        return this.listener;
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
        return new ListasAdapter(listas, listener);
    }

    @Singleton
    @Provides
    List<Lista> providesListas(){
        return new ArrayList<Lista>();
    }
}
