package com.fjbatresv.callrest.contactList;

import com.fjbatresv.callrest.contactList.events.ContactListEvent;
import com.fjbatresv.callrest.contactList.ui.ContactListView;
import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by javie on 29/09/2016.
 */
public class ContactListPresenterImpl implements ContactListPresenter {
    private EventBus bus;
    private ContactListView view;
    private ContactListInteractor interactor;

    public ContactListPresenterImpl(EventBus bus, ContactListView view, ContactListInteractor interactor) {
        this.bus = bus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        bus.register(this);
    }

    @Override
    public void onODestroy() {
        bus.unRegister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThred(ContactListEvent event) {
        view.loading(false);
        if (event.getError() != null && !event.getError().isEmpty()){
            view.showError(event.getError());
        }else{
            switch (event.getTipo()){
                case ContactListEvent.LOAD_CONTACTS:
                    view.loadContacts(event.getContactos());
                    break;
                case ContactListEvent.ADD_CONTACTS:
                    view.contactsAdded();
                    break;
            }
        }
    }

    @Override
    public void loadContacts(String nombre) {
        view.loading(true);
        interactor.loadContacts(nombre);
    }

    @Override
    public void saveContacts(List<Contacto> contactos, String nombre) {
        view.loading(true);
        interactor.saveContacts(contactos, nombre);
    }
}
