package com.fjbatresv.callrest.entities;

import com.fjbatresv.callrest.db.CallRestDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by javie on 6/10/2016.
 */
@Table(database = CallRestDB.class)
public class Llamada extends BaseModel {
    @PrimaryKey
    @Column
    private String id;
    @Column
    private String numero;
    @Column
    private String nombre;
    @Column
    private Date fecha_hora;
    @Column
    private String lista;

    public Llamada() {
    }

    public Llamada(String id, String numero, String nombre, Date fecha_hora, String lista) {
        this.id = id;
        this.numero = numero;
        this.nombre = nombre;
        this.fecha_hora = fecha_hora;
        this.lista = lista;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }
}
