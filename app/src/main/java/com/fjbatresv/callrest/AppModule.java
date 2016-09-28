package com.fjbatresv.callrest;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 27/09/2016.
 */
@Module
public class AppModule {
    private App app;
    public AppModule(App app) {
        this.app = app;
    }
    @Singleton
    @Provides
    Application providesApplication(){
        return this.app;
    }
    @Singleton
    @Provides
    Context providesContext(){
        return this.app.getApplicationContext();
    }
    @Singleton
    @Provides
    SharedPreferences providesSharedPreferences(Application application){
        return application.getSharedPreferences(
                app.getLoggedUserSharedPreferences(),
                Context.MODE_PRIVATE
        );
    }

}
