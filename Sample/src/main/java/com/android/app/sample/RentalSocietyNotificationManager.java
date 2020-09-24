package com.android.app.sample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

public class RentalSocietyNotificationManager {

    private static final String GROUP_ID = "com.wuba.house.GROUP.RentalSocietyNotificationManager";

    private static final String ID = "com.wuba.house.RentalSocietyNotificationManager";

    public static void notify(Context context, String jump, String title, String subtitle, String message) {
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)) {
            NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

            try {
                PendingIntent pendingIntent = getNotifyPendingIntent(context, jump);
                Notification.Builder builder = new Notification.Builder(context, "1");
                builder.setPriority(Notification.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                        .setDefaults(1)
                        .setWhen(System.currentTimeMillis())
                        .setShowWhen(true)
                        .setAutoCancel(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    builder.setChannelId("1");
                    NotificationChannelGroup channelGroup = new NotificationChannelGroup(GROUP_ID, "通知");
                    mNotificationManager.createNotificationChannelGroup(channelGroup);
                    NotificationChannel notificationChannel = new NotificationChannel(ID, "租房福利社消息", NotificationManager.IMPORTANCE_HIGH);
                    notificationChannel.setGroup(GROUP_ID);
                    mNotificationManager.createNotificationChannel(notificationChannel);
                }

                mNotificationManager.notify(String.valueOf(System.currentTimeMillis()), 111, builder.build());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        }
    }

    private static PendingIntent getNotifyPendingIntent(Context context, String jump) {
        Intent intent = new Intent(context, MainActivity.class);
//        Intent intent = PageTransferManager.getJumpIntentByProtocol(context, jump);
        return PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
