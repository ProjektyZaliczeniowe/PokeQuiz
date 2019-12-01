package com.project.pokequiz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;


public class AudioJackReceiver extends BroadcastReceiver {

    private static int plugState = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(AudioManager.ACTION_HEADSET_PLUG)) {
            int state = intent.getIntExtra("state", -1);
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            switch (state) {
                case 0:
                    if(plugState != 0 && plugState != -1)
                        Toast.makeText(context, R.string.headphones_off, Toast.LENGTH_LONG).show();
                    plugState = -1;
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
                    break;
                case 1:
                    if(plugState != 0 && plugState != 1)
                        Toast.makeText(context, R.string.headphones_on, Toast.LENGTH_LONG).show();
                    plugState = 1;
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,  (int)(maxVolume*0.2), 0);
                    break;
                default:
                    break;
            }
        }
    }
}