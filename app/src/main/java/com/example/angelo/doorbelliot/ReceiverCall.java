package com.example.angelo.doorbelliot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by marco on 17/05/2017.
 */

public class ReceiverCall extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startServiceIntent = new Intent(context, MainActivity.class);
        startServiceIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startServiceIntent);    }
}
