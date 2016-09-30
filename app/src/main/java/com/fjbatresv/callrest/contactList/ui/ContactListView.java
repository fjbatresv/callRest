package com.fjbatresv.callrest.contactList.ui;

import com.fjbatresv.callrest.entities.Contacto;

import java.util.List;

/**
 * Created by javie on 29/09/2016.
 */
public interface ContactListView {
    void loading(boolean load);
    void showError(String error);
    void contactsAdded();

    void loadContacts(List<Contacto> contactos);
}
