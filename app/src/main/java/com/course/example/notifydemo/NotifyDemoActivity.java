/*
 * This is an example of using Notifications.
 * There is a permission in the Manifest for the handset to vibrate. 
 */

package com.course.example.notifydemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotifyDemoActivity extends Activity {

	private NotificationManager mNotificationManager;
	private Notification notifyDetails;
	private int SIMPLE_NOTFICATION_ID;
	private String contentTitle = "Simple Notification Example";
	private String contentText = "Get back to Application by clicking me";
	private String tickerText = "New Alert, Click Me !!!";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		//create intent for action when notification selected
		//from expanded status bar 
		Intent notifyIntent = new Intent(this, Widgets.class);
		
		/*
		  Intent notifyIntent = new Intent();
		  notifyIntent.setComponent(new ComponentName("com.course.example",
		                  "com.course.example.IOTest"));
		 */ 

		//create pending intent to wrap intent so that it
		//will fire when notification selected.
		PendingIntent pendingIntent = PendingIntent.getActivity(
				this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		//build notification object and set parameters
		notifyDetails =
				new Notification.Builder(this)
						.setContentIntent(pendingIntent)

						.setContentTitle(contentTitle)   //set Notification text and icon
						.setContentText(contentText)
						.setSmallIcon(R.drawable.droid)

						.setTicker(tickerText)            //sent to accessibility services

						.setWhen(System.currentTimeMillis())    //timestamp when event occurs

						.setAutoCancel(true)     //cancel Notification after clicking on it

						//set Android to vibrate when notified
						.setVibrate(new long[]{1000, 1000, 1000, 1000})

						// flash LED (color, on in millisec, off)
						//doesn't work for all handsets
						.setLights(Integer.MAX_VALUE, 500, 500)

						.build();



		Button start = (Button) findViewById(R.id.btn_showsample);
		Button cancel = (Button) findViewById(R.id.btn_clear);

		start.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				//notify() in response to button click.
				mNotificationManager.notify(SIMPLE_NOTFICATION_ID,
						notifyDetails);
			}
		});

		cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				mNotificationManager.cancel(SIMPLE_NOTFICATION_ID);
			}
		});
	}
}