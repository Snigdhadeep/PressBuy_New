package com.techpenta.pressbuy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Diku on 05-05-2017.
 */

public class MyFirebaseMassegingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent intent=new Intent(this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        dzi53bO8EEg:APA91bHNgH3YbpA5eIxchIJwoeKoLRkiBIyFe4uaoZXRdLxkwxMQF7oIV1l33OJKM3JtKk6h7PiQuJFpufMFAOqZ6vxvY5Q5boNYIDkhChcTWUfTpAcWFr5o-pifRlXLJbcC_LcM32g5
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationbuilder=new NotificationCompat.Builder(this);
        notificationbuilder.setContentTitle("PressBuy");
        notificationbuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationbuilder.setAutoCancel(true);
        notificationbuilder.setSmallIcon(R.mipmap.ic_logo);
        notificationbuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationbuilder.build());
    }
}
