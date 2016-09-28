package com.fjbatresv.callrest.entities;

import com.fjbatresv.callrest.db.CallRestDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by javie on 27/09/2016.
 */
@Table(database = CallRestDB.class)
public class Lista extends BaseModel implements Serializable {
    @PrimaryKey
    @Column
    private String nombre;
    @Column
    private String descripcion;
    private List<Contacto> contactos;

    public Lista() {
    }

    public Lista(String nombre, String descripcion, List<Contacto> contactos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contactos = contactos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }
}
