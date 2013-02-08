package com.cisco.yamba;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver {
	public static final int NOTIFICATION_ID = 47;

	@SuppressLint("NewApi")
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("NotificationReceiver", "onReceive");

		// Get NotificationManager
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		// Build Notification object using Notification.Builder
		// include title, text, and pending intent to open your MainActivity
		PendingIntent operation = PendingIntent.getActivity(context,
				NOTIFICATION_ID, new Intent(context, MainActivity.class),
				PendingIntent.FLAG_UPDATE_CURRENT);
		Notification notification = null;

		if (Build.VERSION.SDK_INT < 16) {
			notification = new Notification.Builder(context)
					.setContentTitle("New Status!")
					.setContentText("You've got a new status.")
					.setContentIntent(operation).getNotification();
		} else {
			notification = new Notification.Builder(context)
					.setContentTitle("New Status!")
					.setContentText("You've got a new status.")
					.setSmallIcon(android.R.drawable.ic_menu_add)
					.setContentIntent(operation).build();
		}

		Log.d("NotificationReceiver", "notification: "+notification);
		// notify() NotificationManager
		manager.notify(NOTIFICATION_ID, notification);

	}
}
