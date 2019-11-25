package com.project.pokequiz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;


public class AudioJackReceiver extends BroadcastReceiver {

    private final String PLUG_IN_MESSAGE = "Słuchawki podłączone";
    private final String PLUG_OFF_MESSAGE = "Odłączono słuchawki";
    private static int plugState = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(AudioManager.ACTION_HEADSET_PLUG)) {
            int state = intent.getIntExtra("state", -1);
            switch (state) {
                case 0:
                    if(plugState != -1)
                        Toast.makeText(context, PLUG_OFF_MESSAGE, Toast.LENGTH_LONG).show();
                    plugState = -1;
                    break;
                case 1:
                    if(plugState != 1 )
                        Toast.makeText(context, PLUG_IN_MESSAGE, Toast.LENGTH_LONG).show();
                    plugState = 1;
                    break;
                default:
                    break;
            }
        }
    }
}