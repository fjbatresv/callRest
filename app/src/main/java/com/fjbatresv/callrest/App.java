package com.fjbatresv.callrest;

import android.app.Application;

import com.fjbatresv.callrest.libs.DI.LibsModule;
import com.fjbatresv.callrest.listas.DI.DaggerListasComponent;
import com.fjbatresv.callrest.listas.DI.ListasComponent;
import com.fjbatresv.callrest.listas.DI.ListasModule;
import com.fjbatresv.callrest.listas.ui.ListasView;
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
    public ListasComponent listas(ListasView view){
        return DaggerListasComponent.builder()
                .appModule(appModule)
                .libsModule(libsModule)
                .listasModule(new ListasModule(view))
                .build();
    }
    //INJECTION -- END
}
