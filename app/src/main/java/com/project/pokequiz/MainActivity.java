package com.project.pokequiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private boolean musicOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        final ImageButton musicButton = findViewById(R.id.imageButton);
        configureBackgroundMusic(musicButton);

        createStartQuizButton();
    }

    private void createStartQuizButton() {
        Button start = super.findViewById(R.id.button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, QuizActivity.class);
                MainActivity.this.startActivity(i);
            }
        });
    }

    private void configureBackgroundMusic(final ImageButton musicButton) {
        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("sharedPreferences", 0);
        musicOn = sharedPreferences.getBoolean("musicOn", true);
        if (musicOn) {
            startService(new Intent(this, BackgroundMusicService.class));
            musicButton.setBackgroundResource(R.drawable.music_on);
        } else {
            musicButton.setBackgroundResource(R.drawable.music_off);
        }

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicOn) {
                    musicOn = false;
                    sharedPreferences.edit().putBoolean("musicOn", false).apply();
                    musicButton.setBackgroundResource(R.drawable.music_off);
                    stopService(new Intent(v.getContext(), BackgroundMusicService.class));
                } else {
                    musicOn = true;
                    sharedPreferences.edit().putBoolean("musicOn", true).apply();
                    musicButton.setBackgroundResource(R.drawable.music_on);
                    startService(new Intent(v.getContext(), BackgroundMusicService.class));
                }
            }
        });
    }
}
