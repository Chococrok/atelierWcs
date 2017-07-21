package fr.ab.servicedemoedward;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Intent myIntent = new Intent(context, MyService.class);
        myIntent.setAction(MyService.INTENT_DISPLAY_NOTIF);
        context.startService(myIntent);
    }

}
