package com.fjbatresv.callrest.libs.DI;

import com.fjbatresv.callrest.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 27/09/2016.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class})
public interface LibsComponent {
}
