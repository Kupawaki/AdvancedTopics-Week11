package com.example.foregroundservicestesting;

import static com.example.foregroundservicestesting.Channel.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ForegroundService extends Service
{
    //onCreate is called only once, even if you start a service multiple times
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    //This is what can be called multiple times
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        String input = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_timer)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        //You can stop a service if you are all done with your work
        //Do heavy work on a background thread
        //stopSelf();

        //If the service is killed it will be restarted, and the intent will be passed as the whatever the last intent was
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}