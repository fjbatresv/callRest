package com.fjbatresv.callrest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.CalendarView;

import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.entities.Contacto_Table;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.entities.Lista_Table;
import com.fjbatresv.callrest.entities.Llamada;
import com.fjbatresv.callrest.entities.Settings;
import com.fjbatresv.callrest.entities.Settings_Table;
import com.fjbatresv.callrest.utils.Crypto;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by javie on 25/09/2016.
 */
public class TelephonyReceiver extends BroadcastReceiver {
    private Context context;
    private Settings settings;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
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
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.e("IDLE", "descansando");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.e("offhook", "ocupado");
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    String incoming = incomingNumber;
                    Calendar date = Calendar.getInstance();
                    Log.e("sonando", "Llamada de: " + incoming);
                    settings = SQLite.select().from(Settings.class).where(Settings_Table.id.eq(1)).querySingle();
                    if(settings == null){
                        settings = new Settings(1, "", false, null, null, null);
                    }
                    List<Contacto> contactos = SQLite.select().from(Contacto.class)
                            .where(Contacto_Table.numero.eq(incoming)).or(Contacto_Table.numero.eq(settings.getExtension() + incoming)).queryList();
                    for (Contacto contacto : contactos) {
                        Log.e("Contacto", contacto.getNombre());
                        Lista lista = SQLite.select().from(Lista.class)
                                .where(Lista_Table.nombre.eq(contacto.getNombreLista())).querySingle();
                        Log.e("Lista", lista.getTipo());
                        if(lista.getTipo().equalsIgnoreCase(context.getResources().getStringArray(R.array.listas_add_tipo)[0])){
                            endCall();
                            new Llamada(Crypto.getRandomUuid(), incomingNumber, contacto.getNombre(), new Date(), lista.getNombre()).save();
                            break;
                        }
                        if (lista.getTipo().equalsIgnoreCase(context.getResources().getStringArray(R.array.listas_add_tipo)[2])
                                && (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                                date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)){
                            endCall();
                            new Llamada(Crypto.getRandomUuid(), incomingNumber, contacto.getNombre(), new Date(), lista.getNombre()).save();
                            sendSms(contacto, settings.getSmsNoWeekend());
                            break;
                        }
                        if (lista.getTipo().equalsIgnoreCase(context.getResources().getStringArray(R.array.listas_add_tipo)[3])
                                && (date.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
                                || date.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY
                                || date.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY
                                || date.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY
                                || date.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                                && date.get(Calendar.HOUR_OF_DAY) >= 8
                                && date.get(Calendar.HOUR_OF_DAY) <= 17){
                            endCall();
                            new Llamada(Crypto.getRandomUuid(), incomingNumber, contacto.getNombre(), new Date(), lista.getNombre()).save();
                            sendSms(contacto, settings.getSmsNoWork());
                            break;
                        }
                        if (lista.getTipo().equalsIgnoreCase(context.getResources().getStringArray(R.array.listas_add_tipo)[4])
                                || ((date.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
                                || date.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY
                                || date.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY
                                || date.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY
                                || date.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                                && date.get(Calendar.HOUR_OF_DAY) <= 8
                                && date.get(Calendar.HOUR_OF_DAY) >= 17)
                                || date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                                || date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                            Log.e("solo trabajo", "solamente en el tiempo de trabajo");
                            endCall();
                            new Llamada(Crypto.getRandomUuid(), incomingNumber, contacto.getNombre(), new Date(), lista.getNombre()).save();
                            sendSms(contacto, settings.getSmsJustWork());
                            break;
                        }
                    }
            }
        }

        private void endCall() {
            try {
                Class c = Class.forName(tm.getClass().getName());
                Method m = c.getDeclaredMethod("getITelephony");
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

    private void sendSms(Contacto contacto, String sms) {
        if (settings.getSms()){
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(contacto.getNumero(), null, sms, null, null);
        }
    }
}
