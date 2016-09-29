package com.fjbatresv.callrest.listas.add.DI;

import android.content.Context;

import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.listas.add.ListaAddInteractor;
import com.fjbatresv.callrest.listas.add.ListaAddInteractorImpl;
import com.fjbatresv.callrest.listas.add.ListaAddPresenter;
import com.fjbatresv.callrest.listas.add.ListaAddPresenterImpl;
import com.fjbatresv.callrest.listas.add.ListaAddRepo;
import com.fjbatresv.callrest.listas.add.ListaAddRepoImpl;
import com.fjbatresv.callrest.listas.add.ui.ListaAddView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 29/09/2016.
 */
@Module
public class ListaAddModule {
    private ListaAddView view;

    public ListaAddModule(ListaAddView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    ListaAddView providesListaAddView(){
        return this.view;
    }

    @Singleton
    @Provides
    ListaAddPresenter providesListaAddPresenter(EventBus bus, ListaAddView view, ListaAddInteractor interactor){
        return new ListaAddPresenterImpl(bus, view, interactor);
    }

    @Singleton
    @Provides
    ListaAddInteractor providesListaAddInteractor(EventBus bus, ListaAddRepo repo, Context context){
        return new ListaAddInteractorImpl(bus, repo, context);
    }

    @Singleton
    @Provides
    ListaAddRepo providesListaAddRepo(EventBus bus, Context context){
        return new ListaAddRepoImpl(bus, context);
    }
}
