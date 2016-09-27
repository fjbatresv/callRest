package com.fjbatresv.callrest;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private static final String MANUFACTURER_HTC = "HTC";
    private static final int PERMISSIONS_REQUEST_LOCATION = 1;

    private KeyguardManager keyguardManager;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyPpermissions();
    }

    private void verifyPpermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){//Marshmallow o superior
                requestPermissions(new String[]{
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.MODIFY_PHONE_STATE,
                        Manifest.permission.READ_PHONE_STATE
                }, PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length == 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "no hay permisos", Toast.LENGTH_LONG).show();
                }else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "PERMISOS OK", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    class PhoneListener extends PhoneStateListener{
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
                    endCall();
                    break;
            }
        }

        private void endCall(){
            TelephonyManager tm  = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
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
