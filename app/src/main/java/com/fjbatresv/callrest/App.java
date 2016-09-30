package com.fjbatresv.callrest;

import android.app.Application;

import com.fjbatresv.callrest.contactList.DI.ContactListComponent;
import com.fjbatresv.callrest.contactList.DI.ContactListModule;
import com.fjbatresv.callrest.contactList.DI.DaggerContactListComponent;
import com.fjbatresv.callrest.contactList.ui.ContactListView;
import com.fjbatresv.callrest.contactList.ui.adapters.ContactListOnItemClickListener;
import com.fjbatresv.callrest.libs.DI.LibsModule;
import com.fjbatresv.callrest.listas.add.DI.DaggerListaAddComponent;
import com.fjbatresv.callrest.listas.add.DI.ListaAddComponent;
import com.fjbatresv.callrest.listas.add.DI.ListaAddModule;
import com.fjbatresv.callrest.listas.add.ui.ListaAddView;
import com.fjbatresv.callrest.listas.list.DI.DaggerListasComponent;
import com.fjbatresv.callrest.listas.list.DI.ListasComponent;
import com.fjbatresv.callrest.listas.list.DI.ListasModule;
import com.fjbatresv.callrest.listas.list.ui.ListasView;
import com.fjbatresv.callrest.listas.list.ui.adapters.OnItemClickListener;
import com.fjbatresv.callrest.listas.view.DI.DaggerListaViewComponent;
import com.fjbatresv.callrest.listas.view.DI.ListaViewComponent;
import com.fjbatresv.callrest.listas.view.DI.ListaViewModule;
import com.fjbatresv.callrest.listas.view.ui.ListaViewView;
import com.fjbatresv.callrest.listas.view.ui.adapters.ListaViewOnItemClickListener;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by javie on 27/09/2016.
 */
public class App extends Application {
    private String shared = "CallRestSharedPref";
    private LibsModule libsModule;
    private AppModule appModule;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        libsModule = new LibsModule();
        appModule = new AppModule(this);
        FlowManager.init(this);
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        DbTearDown();
    }
    private void DbTearDown(){
        FlowManager.destroy();
    }


    public String getLoggedUserSharedPreferences() {
        return shared;
    }

    //INJECTION -- START
    public ListasComponent listas(ListasView view, OnItemClickListener listener){
        return DaggerListasComponent.builder()
                .appModule(appModule)
                .libsModule(libsModule)
                .listasModule(new ListasModule(view, listener))
                .build();
    }

    public ListaAddComponent listaAdd(ListaAddView view){
        return DaggerListaAddComponent.builder()
                .appModule(appModule)
                .libsModule(libsModule)
                .listaAddModule(new ListaAddModule(view))
                .build();
    }

    public ListaViewComponent listaView(ListaViewView view, ListaViewOnItemClickListener listener){
        return DaggerListaViewComponent.builder()
                .appModule(appModule)
                .libsModule(libsModule)
                .listaViewModule(new ListaViewModule(view, listener))
                .build();
    }

    public ContactListComponent contactList(ContactListView view, ContactListOnItemClickListener listener){
        return DaggerContactListComponent.builder()
                .appModule(appModule)
                .libsModule(libsModule)
                .contactListModule(new ContactListModule(view, listener))
                .build();
    }
    //INJECTION -- END
}
