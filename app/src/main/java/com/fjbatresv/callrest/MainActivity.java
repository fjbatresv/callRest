package com.fjbatresv.callrest;

import android.app.KeyguardManager;
import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private static final String MANUFACTURER_HTC = "HTC";

    private KeyguardManager keyguardManager;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager tm = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        Class c = null;
        try {
            c = Class.forName(tm.getClass().getName());
            try {
                Method m = c.getDeclaredMethod("getITelephony");
                m.setAccessible(true);
                ITelephony telephonyService = (ITelephony)m.invoke(tm);
            } catch (NoSuchMethodException e) {
                Log.e("ex1", e.toString());
            } catch (InvocationTargetException e) {
                Log.e("ex2", e.toString());
            } catch (IllegalAccessException e) {
                Log.e("ex3", e.toString());
            } catch (Exception ex) {
                Log.e("ex4", ex.toString());
            }
        } catch (ClassNotFoundException e) {
            Log.e("ex5", e.toString());
        } catch (Exception ex) {
            Log.e("ex6", ex.toString());
        }
    }
}
