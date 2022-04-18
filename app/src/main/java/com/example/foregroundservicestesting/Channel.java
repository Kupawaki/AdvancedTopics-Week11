package com.example.foregroundservicestesting;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


public class Channel extends Application
{
    public static final String CHANNEL_ID = "exampleServiceChannel";

    @Override
    public void onCreate()
    {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel()
    {
        NotificationChannel serviceChannel = new NotificationChannel(
                CHANNEL_ID,
                "Foreground Channel",
                NotificationManager.IMPORTANCE_DEFAULT
        );

        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(serviceChannel);
    }
}