package com.anjlab.android.fx.c2dm;

import com.anjlab.android.fx.AppTools;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/*
 append these lines to your manifest file:
 
    <permission android:name="<your-package-name>.permission.C2D_MESSAGE" android:protectionLevel="signature" />
    <uses-permission android:name="<your-package-name>.permission.C2D_MESSAGE" />
    <uses-permission android:name="com.google.android.c2dm.permission.RECEIVE" />
    
    and also under <application> tag:
    
    <receiver android:name="C2dmReceiver<your implementation class>" android:permission="com.google.android.c2dm.permission.SEND" >
      <intent-filter>
        <action android:name="com.google.android.c2dm.intent.RECEIVE" />
        <category android:name="<your-package-name>" />
      </intent-filter>
      <intent-filter>
        <action android:name="com.google.android.c2dm.intent.REGISTRATION" />
        <category android:name="<your-package-name>" />
      </intent-filter>
    </receiver>
    
 to get registration tocken use this code:
	Intent registrationIntent = new Intent("com.google.android.c2dm.intent.REGISTER");
	registrationIntent.putExtra("app", PendingIntent.getBroadcast(ctx, 0, new Intent(), 0));
	registrationIntent.putExtra("sender", <your c2dm sender email>);
	startService(registrationIntent);
*/
public abstract class C2dmReceiver extends BroadcastReceiver {
	private static final String LOG_TAG = "fx-c2dm";
	private static String REGISTRATION_KEY = "c2dm_registration_key";

	public static String getRegistrationKey(Context context) {
		return AppTools.getSettingsValueAsSting(context, REGISTRATION_KEY, null);
	}
	
	private static void setRegistrationKey(Context context, String value) {
		AppTools.setSettingsValue(context, REGISTRATION_KEY, value);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals("com.google.android.c2dm.intent.REGISTRATION")) {
			handleRegistration(context, intent);
		} else if (intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
			onMessageReceived(context, intent);
		}
	}

	private void handleRegistration(Context context, Intent intent) {
		String registration = intent.getStringExtra("registration_id");
		if (intent.getStringExtra("error") != null) {
			// Registration failed, should try again later.
			Log.d(LOG_TAG, "registration failed");
			String error = intent.getStringExtra("error");
			
			if(error == "SERVICE_NOT_AVAILABLE") {
				Log.d(LOG_TAG, "SERVICE_NOT_AVAILABLE");
			}else if(error == "ACCOUNT_MISSING") {
				Log.d(LOG_TAG, "ACCOUNT_MISSING");
			}else if(error == "AUTHENTICATION_FAILED") {
				Log.d(LOG_TAG, "AUTHENTICATION_FAILED");
			}else if(error == "TOO_MANY_REGISTRATIONS") {
				Log.d(LOG_TAG, "TOO_MANY_REGISTRATIONS");
			}else if(error == "INVALID_SENDER") {
				Log.d(LOG_TAG, "INVALID_SENDER");
			}else if(error == "PHONE_REGISTRATION_ERROR") {
				Log.d(LOG_TAG, "PHONE_REGISTRATION_ERROR");
			}
		} else if (intent.getStringExtra("unregistered") != null) {
			// unregistration done, new messages from the authorized sender will be rejected
			Log.d(LOG_TAG, "unregistered");
		} else if (registration != null) {
			Log.d(LOG_TAG, registration);
			setRegistrationKey(context, registration);
			onRegistrationKeyReceived(registration);
		}
	}
	
	public void onRegistrationKeyReceived(String key) {
		// Send the registration ID to the 3rd party site that is sending the messages.
		// This should be done in a separate thread.
	}

	protected abstract void onMessageReceived(Context context, Intent intent);

	protected void displayNotification(Context ctx, int iconResId, String title, String message, Intent intent, int id) {
		NotificationManager mNotificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(iconResId, message, System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		notification.setLatestEventInfo(ctx, title, message, contentIntent);
		mNotificationManager.notify(id, notification);
	}
}
