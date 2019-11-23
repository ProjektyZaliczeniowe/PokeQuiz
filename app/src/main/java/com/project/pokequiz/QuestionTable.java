package com.project.pokequiz;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class QuestionTable {
    public static final String TABLE_NAME = "question";

    public static class QuestionColumns implements BaseColumns {
        public static final String BASE64IMAGE = "base_64_image";
        public static final String WRONGANSWER1 = "wrong_answer_1";
        public static final String WRONGANSWER2 = "wrong_answer_2";
        public static final String WRONGANSWER3 = "wrong_answer_3";
        public static final String GOODANSWER = "good_answer";
    }
    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + QuestionTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(QuestionColumns.BASE64IMAGE + " BLOB, ");
        sb.append(QuestionColumns.WRONGANSWER1 + " TEXT, ");
        sb.append(QuestionColumns.WRONGANSWER2 + " TEXT, ");
        sb.append(QuestionColumns.WRONGANSWER3 + " TEXT, ");
        sb.append(QuestionColumns.GOODANSWER + " TEXT ");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db,
                                 int oldVersion,
                                 int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "
                + QuestionTable.TABLE_NAME);
        QuestionTable.onCreate(db);
    }
}
