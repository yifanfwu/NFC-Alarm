package com.clv69.alarmnfc;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.Vibrator;
import android.widget.Toast;

import java.io.PipedOutputStream;

public class AlarmManagerHelper extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager powerManager = (PowerManager) context.getSystemService(
                Context.POWER_SERVICE);

        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK |
             PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, MainActivity.APP_TAG);

        long pattern[] = new long[]{0, 500, 500, 500, 500};

//        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            wakeLock.acquire();

            Toast.makeText(context, "Time to get up!",
                    Toast.LENGTH_LONG).show();

            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(pattern, 0);
            wakeLock.release();
//        }

    }
}
