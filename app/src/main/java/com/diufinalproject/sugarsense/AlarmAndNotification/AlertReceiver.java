package com.diufinalproject.sugarsense.AlarmAndNotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    static int id =1;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(intent.getStringExtra("time"),intent.getStringExtra("msg"));
        notificationHelper.getManager().notify(id++, nb.build());
    }
}
