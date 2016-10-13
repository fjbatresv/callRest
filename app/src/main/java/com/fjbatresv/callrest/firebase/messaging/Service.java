package com.fjbatresv.callrest.firebase.messaging;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by javie_000 on 10/13/2016.
 */
public class Service extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("FIREBASE", "De: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0){
            Log.e("FIREBASE", "Data Payload: " + remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Log.d("FIREBASE", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }
}
