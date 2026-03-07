package com.andreafilice.lavorami;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class LavoraMiMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (!DataManager.getBoolData(this, DataKeys.KEY_NOTIFICATION_PUSH, true)) return;
        if (!DataManager.getBoolData(this, DataKeys.KEY_NOTIFICATION_SWITCH, true)) return;

        String title = "LavoraMi";
        String message = "";

        if (remoteMessage.getNotification() != null) {
            title = remoteMessage.getNotification().getTitle();
            message = remoteMessage.getNotification().getBody();
        }

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            manager.createNotificationChannel(new NotificationChannel("lavorami_notifications", "Avvisi LavoraMi", NotificationManager.IMPORTANCE_HIGH));

        manager.notify((int) System.currentTimeMillis(),
            new NotificationCompat.Builder(this, "lavorami_notifications")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build()
        );
    }
}