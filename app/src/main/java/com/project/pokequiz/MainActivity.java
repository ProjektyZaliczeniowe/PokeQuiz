package com.project.pokequiz;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private boolean musicOn;
    private AudioJackReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver = new AudioJackReceiver();
        Objects.requireNonNull(getSupportActionBar()).hide();

        final ImageButton musicButton = findViewById(R.id.imageButton);
        configureBackgroundMusic(musicButton);

        createStartQuizButton();

        SQLiteOpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getWritableDatabase();

    }

    @Override public void onResume() {
        IntentFilter filter = new IntentFilter(AudioManager.ACTION_HEADSET_PLUG);
        filter.setPriority(1000);
        registerReceiver(receiver, filter);
        super.onResume();
    }

    private void createStartQuizButton() {
        Button start = super.findViewById(R.id.button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteOpenHelper openHelper = new OpenHelper(v.getContext());
                SQLiteDatabase db = openHelper.getWritableDatabase();
                QuestionDao questionDao = new QuestionDao(db);
                List<Question> questionList = questionDao.getAll();
                Collections.shuffle(questionList);
                Intent i = new Intent(MainActivity.this, QuizActivity.class);
                i.putExtra("QuestionList", (Serializable) questionList);
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
