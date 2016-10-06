package com.fjbatresv.callrest.entities;

import com.fjbatresv.callrest.db.CallRestDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by javie on 5/10/2016.
 */
@Table(database = CallRestDB.class)
public class Settings extends BaseModel {
    @PrimaryKey
    @Column
    private int id;
    @Column
    private String extension;
    @Column
    private boolean sms;
    @Column
    private String smsNoWeekend;
    @Column
    private String smsNoWork;
    @Column
    private String smsJustWork;

    public Settings() {
    }

    public Settings(int id, String extension, boolean sms, String smsNoWeekend, String smsNoWork, String smsJustWork) {
        this.id = id;
        this.extension = extension;
        this.sms = sms;
        this.smsNoWeekend = smsNoWeekend;
        this.smsNoWork = smsNoWork;
        this.smsJustWork = smsJustWork;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean getSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }

    public String getSmsNoWeekend() {
        return smsNoWeekend;
    }

    public void setSmsNoWeekend(String smsNoWeekend) {
        this.smsNoWeekend = smsNoWeekend;
    }

    public String getSmsNoWork() {
        return smsNoWork;
    }

    public void setSmsNoWork(String smsNoWork) {
        this.smsNoWork = smsNoWork;
    }

    public String getSmsJustWork() {
        return smsJustWork;
    }

    public void setSmsJustWork(String smsJustWork) {
        this.smsJustWork = smsJustWork;
    }
}
