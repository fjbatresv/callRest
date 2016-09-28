package com.fjbatresv.callrest.utils;

import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.entities.Contacto_Table;
import com.fjbatresv.callrest.entities.Lista;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javie on 27/09/2016.
 */
public class Queries {
    public static List<Lista> listasConHijos(){
        List<Lista> listas = new ArrayList<Lista>();
        listas = SQLite.select().from(Lista.class).queryList();
        for (Lista lista : listas) {
            List<Contacto> contactos = SQLite.select().from(Contacto.class)
                    .where(Contacto_Table.nombreLista.eq(lista.getNombre()))
                    .queryList();
            lista.setContactos(contactos);
        }
        return listas;
    }
}
