package com.fjbatresv.callrest.contactList.DI;

import android.content.Context;

import com.fjbatresv.callrest.contactList.ContactListInteractor;
import com.fjbatresv.callrest.contactList.ContactListInteractorImpl;
import com.fjbatresv.callrest.contactList.ContactListPresenter;
import com.fjbatresv.callrest.contactList.ContactListPresenterImpl;
import com.fjbatresv.callrest.contactList.ContactListRepo;
import com.fjbatresv.callrest.contactList.ContactListRepoImpl;
import com.fjbatresv.callrest.contactList.ui.ContactListView;
import com.fjbatresv.callrest.contactList.ui.adapters.ContactListAdapter;
import com.fjbatresv.callrest.contactList.ui.adapters.ContactListOnItemClickListener;
import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.libs.base.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 30/09/2016.
 */
@Module
public class ContactListModule {
    private ContactListView view;
    private ContactListOnItemClickListener listener;

    public ContactListModule(ContactListView view, ContactListOnItemClickListener listener) {
        this.view = view;
        this.listener = listener;
    }

    @Singleton
    @Provides
    ContactListView providesContactListView(){
        return this.view;
    }

    @Singleton
    @Provides
    ContactListOnItemClickListener providesContactListOnItemClickListener(){
        return this.listener;
    }

    @Singleton
    @Provides
    ContactListPresenter providesContactListPresenter(EventBus bus, ContactListView view, ContactListInteractor interactor){
        return new ContactListPresenterImpl(bus, view, interactor);
    }

    @Singleton
    @Provides
    ContactListInteractor providesContactListInteractor(EventBus bus, ContactListRepo repo, Context context){
        return new ContactListInteractorImpl(bus, repo, context);
    }

    @Singleton
    @Provides
    ContactListRepo providesContactListRepo(EventBus bus, Context context){
        return new ContactListRepoImpl(bus, context);
    }

    @Singleton
    @Provides
    ContactListAdapter providesContactListAdapter(List<Contacto> contactos, List<Contacto> selected,
                                                  ContactListOnItemClickListener listener){
        return new ContactListAdapter(contactos, selected, listener);
    }

    @Singleton
    @Provides
    List<Contacto> providesContacts(){
        return new ArrayList<Contacto>();
    }
}
