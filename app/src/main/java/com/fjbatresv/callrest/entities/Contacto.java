package com.fjbatresv.callrest.entities;

import com.fjbatresv.callrest.db.CallRestDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by javie on 27/09/2016.
 */
@Table(database = CallRestDB.class)
public class Contacto extends BaseModel {
    @PrimaryKey
    @Column
    private String id;
    @Column
    private String nombre;
    @Column
    private String numero;
    @Column
    private String nombreLista;

    public Contacto() {
    }

    public Contacto(String id, String nombre, String numero, String nombreLista) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.nombreLista = nombreLista;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }
}
