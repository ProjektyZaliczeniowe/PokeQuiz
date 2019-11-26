package com.project.pokequiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class OpenHelper extends SQLiteOpenHelper {

    private Context context;
    public OpenHelper(final Context context) {
        super(context, "quiz", null,
                1);
        this.context = context;
    }
    @Override
    public void onOpen(final SQLiteDatabase db) {
        super.onOpen(db);
    }
    @Override
    public void onCreate(final SQLiteDatabase db) {
        QuestionTable.onCreate(db);

        int resourceId = R.raw.do_bazy;
        InputStream iStream = context.getResources().openRawResource(resourceId);
        ByteArrayOutputStream byteStream = null;
        try {
            byte[] buffer = new byte[iStream.available()];
            iStream.read(buffer);
            byteStream = new ByteArrayOutputStream();
            byteStream.write(buffer);
            byteStream.close();
            iStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s =  byteStream.toString();
        String[] split = s.split("\n");

        QuestionDao questionDao = new QuestionDao(db);

        for(String el: split) {
            String[] values = el.split(", ");
            Question pytanie = new Question(values[0], values[1], values[2], values[3], values[4]);
            questionDao.save(pytanie);
        }

    }
    @Override
    public void onUpgrade(final SQLiteDatabase db,
                          final int oldVersion, final int newVersion) {
        QuestionTable.onUpgrade(db, oldVersion, newVersion);
    }
}
