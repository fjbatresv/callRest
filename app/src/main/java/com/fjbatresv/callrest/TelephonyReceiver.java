package com.fjbatresv.callrest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.entities.Contacto_Table;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.entities.Lista_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by javie on 25/09/2016.
 */
public class TelephonyReceiver extends BroadcastReceiver {
    private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(new PhoneListener(tm), PhoneStateListener.LISTEN_CALL_STATE);
    }

    class PhoneListener extends PhoneStateListener {
        private TelephonyManager tm;

        public PhoneListener(TelephonyManager tm) {
            this.tm = tm;
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state){
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.e("IDLE", "descansando");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.e("offhook", "ocupado");
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    String incoming = incomingNumber;
                    Log.e("sonando", "Llamada de: " + incoming);
                    List<Contacto> contactos = SQLite.select().from(Contacto.class)
                            .where(Contacto_Table.numero.eq(incoming)).queryList();
                    for (Contacto contacto : contactos) {
                        Lista lista = SQLite.select().from(Lista.class)
                                .where(Lista_Table.nombre.eq(contacto.getNombreLista())).querySingle();
                        if (lista.getTipo().equalsIgnoreCase(context.getResources().getStringArray(R.array.listas_add_tipo)[0])){
                            endCall();
                        }
                    }
                    //endCall();
                    break;
            }
        }

        private void endCall(){
            try {
                Class c = Class.forName(tm.getClass().getName());
                Method m  = c.getDeclaredMethod("getITelephony");
                m.setAccessible(true);
                Object telephonyService = m.invoke(tm);
                c = Class.forName(telephonyService.getClass().getName());
                m = c.getDeclaredMethod("endCall");
                m.setAccessible(true);
                m.invoke(telephonyService);
                Log.e("rechazo", "llamada rechazada");
            } catch (Exception ex) {
                Log.e("rechazoEX", ex.toString() + " | CAUSA: " + ex.getCause().toString());
                ex.printStackTrace();
            }
        }
    }
}
