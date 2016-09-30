package com.fjbatresv.callrest.contactList.ui.adapters;

import com.fjbatresv.callrest.entities.Contacto;

/**
 * Created by javie on 30/09/2016.
 */
public interface ContactListOnItemClickListener {
    void addSelected(Contacto contacto);
    void removeSelected(Contacto contacto);
}
