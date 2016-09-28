package com.fjbatresv.callrest.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by javie on 27/09/2016.
 */
@Database(name = CallRestDB.name, version = CallRestDB.version)
public class CallRestDB {
    public final static int version = 3;
    public final static String name = "CallRest";
}
