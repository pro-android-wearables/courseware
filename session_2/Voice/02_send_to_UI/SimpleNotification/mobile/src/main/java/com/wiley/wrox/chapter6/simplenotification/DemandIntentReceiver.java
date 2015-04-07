package com.wiley.wrox.chapter6.simplenotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class DemandIntentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("wrox", "got data");

        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);

        if (remoteInput.getCharSequence(MyActivityPhone.EXTRA_VOICE_REPLY) != null) {
            CharSequence reply = remoteInput.getCharSequence(MyActivityPhone.EXTRA_VOICE_REPLY);

            Log.v("wrox", "User reply from wearable: " + reply);

            Intent localIntent = new Intent("com.wiley.wrox.chapter6.simplenotification.localIntent");
            localIntent.putExtra("result", reply.toString());
            LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent);
        }
    }
}
