package com.wiley.wrox.chapter6.simplenotification;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;


public class MyActivityPhone extends Activity {

    int NOTIFICATION_ID = 006; // Use chapter number as ID
    NotificationManagerCompat mNotificationManager;

    public static final String EXTRA_MESSAGE = "extra_message";
    public static final String ACTION_DEMAND = "action_demand";
    public static final String EXTRA_VOICE_REPLY = "extra_voice_reply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_phone);

        mNotificationManager = NotificationManagerCompat.from(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity_phone, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void voiceNotification(View v) {

        Log.v("wrox", "Handheld sent notification");

        // Create the intent and pending intent for the notification
        Intent replyIntent = new Intent(this, DemandIntentReceiver.class)
                .putExtra(EXTRA_MESSAGE, "Reply selected.")
                .setAction(ACTION_DEMAND);
        PendingIntent replyPendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0,
            replyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the remote input
        String replyLabel = getResources().getString(R.string.reply_label);
        RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY)
            .setLabel(replyLabel)
            .build();

        // Create the reply action and add the remote input
        NotificationCompat.Action action =
            new NotificationCompat.Action.Builder(R.drawable.wrox_logo_small,
                getString(R.string.reply_label), replyPendingIntent)
                .addRemoteInput(remoteInput)
                .build();

        // Create the notification
        Notification replyNotification = new NotificationCompat.Builder(this)
            .setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .setContentTitle("Voice to handheld")
            .setContentText("Left-swipe and do a voice reply")
            .extend(new NotificationCompat.WearableExtender().addAction(action))
            .build();

        // Issue the notification
        notif(replyNotification);
    }

    private void notif(Notification n) {
        mNotificationManager.notify(NOTIFICATION_ID, n);
    }
}