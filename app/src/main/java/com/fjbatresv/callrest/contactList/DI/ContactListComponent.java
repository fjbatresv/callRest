package com.fjbatresv.callrest.contactList.DI;

import com.fjbatresv.callrest.AppModule;
import com.fjbatresv.callrest.contactList.ui.ContacListActivity;
import com.fjbatresv.callrest.libs.DI.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 30/09/2016.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class, ContactListModule.class})
public interface ContactListComponent {
    void inject(ContacListActivity activity);
}
