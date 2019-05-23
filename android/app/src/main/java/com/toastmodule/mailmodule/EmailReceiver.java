package com.toastmodule.mailmodule;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.toastmodule.R;

public class EmailReceiver extends BroadcastReceiver{

    Context cntxt;
    private static final String TAG = "EmailReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "onReceive: "+intent.getAction());


        Toast.makeText(context, "Email Received", Toast.LENGTH_LONG).show();
//        showNotification(context);
    }

//    private void showNotification(Context context) {
//
//        Intent notificationIntent = new Intent(context, YOUR_ACTIVITY_HERE.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(context,
//                0, notificationIntent,
//                PendingIntent.FLAG_CANCEL_CURRENT);
//
//        NotificationManager nm = (NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//
//        Resources res = context.getResources();
//        Notification.Builder builder = new Notification.Builder(context);
//
//        builder.setContentIntent(contentIntent)
//                .setTicker(res.getString(R.string.app_name))
//                .setWhen(System.currentTimeMillis())
//                .setAutoCancel(true)
//                .setContentTitle(res.getString(R.string.app_name))
//                .setContentText(res.getString(R.string.app_name));
//        Notification n = builder.getNotification();
//        nm.notify(1, n);
//    }
}