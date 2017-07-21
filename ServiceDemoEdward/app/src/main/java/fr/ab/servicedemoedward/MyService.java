package fr.ab.servicedemoedward;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.RequiresApi;
import android.util.Log;

public class MyService extends Service {

    public static final String INTENT_DISPLAY_NOTIF = "INTENT_DISPLAY_NOTIF";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null){
         if(intent.getAction().equals(INTENT_DISPLAY_NOTIF)){
             Log.e("lala","lala");
             displayNotification();
         }
         if(intent.getAction().equals(intent.ACTION_BOOT_COMPLETED));
            displayNotification();

        }

        return START_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void displayNotification(){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification noti = new Notification.Builder(this)
                .setContentTitle("Hello World")
                .setContentText("Youhou")
                .setSmallIcon(R.mipmap.chicken)
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0))
                .build();

        notificationManager.notify((int) Math.random(), noti);
    }
}
