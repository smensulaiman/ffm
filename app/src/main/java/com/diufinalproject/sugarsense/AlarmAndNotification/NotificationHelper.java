package com.diufinalproject.sugarsense.AlarmAndNotification;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.diufinalproject.sugarsense.R;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification(String time, String text) {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(time)
                .setContentText(text)
                .setSound(Settings.System.DEFAULT_ALARM_ALERT_URI)
                .setSmallIcon(R.drawable.ic_alarm_on_black_24dp);

    }
}