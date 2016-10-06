package com.fjbatresv.callrest.contactList;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.fjbatresv.callrest.contactList.events.ContactListEvent;
import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.libs.base.EventBus;
import com.fjbatresv.callrest.utils.Crypto;
import com.fjbatresv.callrest.utils.Queries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javie on 29/09/2016.
 */
public class ContactListRepoImpl implements ContactListRepo{
    private EventBus bus;
    private Context context;

    public ContactListRepoImpl(EventBus bus, Context context) {
        this.bus = bus;
        this.context = context;
    }

    @Override
    public void loadContacts(String nombre) {
        List<Contacto> phone = new ArrayList<Contacto>();
        List<Contacto> contactos = Queries.getContactos(nombre);
        Cursor cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            if ("1".equals(hasPhone) || Boolean.parseBoolean(hasPhone)) {
                Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                while (phones.moveToNext()) {
                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace(" ", "");
                    Contacto contacto = new Contacto(null, name, phoneNumber, nombre);
                    if (!contactos.contains(contacto) && !phone.contains(contacto)){
                        phone.add(contacto);
                    }
                }
                phones.close();
            }
        }
        cursor.close();
        bus.post(new ContactListEvent(ContactListEvent.LOAD_CONTACTS, phone));
    }

    @Override
    public void saveContacts(List<Contacto> contactos, String nombre) {
        for (Contacto contacto : contactos) {
            contacto.setId(Crypto.getRandomUuid());
            contacto.save();
        }
        bus.post(new ContactListEvent(ContactListEvent.ADD_CONTACTS));
    }
}
