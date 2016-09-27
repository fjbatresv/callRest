package com.fjbatresv.callrest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.Method;

/**
 * Created by javie on 25/09/2016.
 */
public class TelephonyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Log.e("1", "receive");
            if (intent != null && intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
                String numero = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
                Log.e("llamando", numero);
            } else {
                String newPhoneState = intent.hasExtra(TelephonyManager.EXTRA_STATE) ?
                        intent.getStringExtra(TelephonyManager.EXTRA_STATE) : null;
                Bundle bundle = intent.getExtras();
                if (newPhoneState != null && newPhoneState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    //Llamada entrante
                    String phoneNumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    Log.e("llamadaEntrante", phoneNumber);
                } else if (newPhoneState != null && newPhoneState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                    Log.e("IDLE", "Telefono libre");
                } else if (newPhoneState != null && newPhoneState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                    Log.e("OFF", "Telefono ocupado");
                }
            }
        } catch (Exception ex) {
            Log.e("ReceiverEX", ex.toString());
        }
    }
}
