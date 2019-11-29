package com.project.pokequiz;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class QuestionDao implements Dao<Question> {

    private static final String INSERT =
            "insert into " + QuestionTable.TABLE_NAME
                    + "(" + QuestionTable.QuestionColumns.IMAGE_NAME + ", " + QuestionTable.QuestionColumns.WRONGANSWER1
                    + ", " + QuestionTable.QuestionColumns.WRONGANSWER2 + ", " + QuestionTable.QuestionColumns.WRONGANSWER3
                    + ", " + QuestionTable.QuestionColumns.GOODANSWER +") " +
                    "values (?, ?, ?, ?, ?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public QuestionDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(QuestionDao.INSERT);
    }

    @Override
    public long save(Question entity) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, entity.getImageName());
        insertStatement.bindString(2, entity.getWrongAnswer1());
        insertStatement.bindString(3, entity.getWrongAnswer2());
        insertStatement.bindString(4, entity.getWrongAnswer3());
        insertStatement.bindString(5, entity.getGoodAnswer());
        return insertStatement.executeInsert();

    }

    @Override
    public void update(Question entity) {
        final ContentValues values = new ContentValues();
        values.put(QuestionTable.QuestionColumns.IMAGE_NAME, entity.getImageName());
        values.put(QuestionTable.QuestionColumns.WRONGANSWER1, entity.getWrongAnswer1());
        values.put(QuestionTable.QuestionColumns.WRONGANSWER2, entity.getWrongAnswer2());
        values.put(QuestionTable.QuestionColumns.WRONGANSWER3, entity.getWrongAnswer3());
        values.put(QuestionTable.QuestionColumns.GOODANSWER, entity.getGoodAnswer());
        db.update(QuestionTable.TABLE_NAME, values,
                BaseColumns._ID + " = ?", new String[] {
                        String.valueOf(entity.getId()) });
    }

    @Override
    public void delete(Question entity) {
        if (entity.getId() > 0) {
            db.delete(QuestionTable.TABLE_NAME,
                    BaseColumns._ID + " = ?", new String[]
                            { String.valueOf(entity.getId()) });
        }
    }

    @Override
    public Question get(long id) {
        Question question = null;
        Cursor c =
                db.query(QuestionTable.TABLE_NAME,
                        new String[] {
                                BaseColumns._ID,
                                QuestionTable.QuestionColumns.IMAGE_NAME,
                                QuestionTable.QuestionColumns.WRONGANSWER1,
                                QuestionTable.QuestionColumns.WRONGANSWER2,
                                QuestionTable.QuestionColumns.WRONGANSWER3,
                                QuestionTable.QuestionColumns.GOODANSWER },
                        BaseColumns._ID + " = ?", new String[] { String.valueOf(id) },
                        null, null, null, "1");
        if (c.moveToFirst()) {
            question = this.buildQuestionFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return question;
    }

    @Override
    public List<Question> getAll() {
        List<Question> list = new ArrayList<Question>();
        Cursor c =
                db.query(QuestionTable.TABLE_NAME, new String[] {
                                BaseColumns._ID,
                                QuestionTable.QuestionColumns.IMAGE_NAME,
                                QuestionTable.QuestionColumns.WRONGANSWER1,
                                QuestionTable.QuestionColumns.WRONGANSWER2,
                                QuestionTable.QuestionColumns.WRONGANSWER3,
                                QuestionTable.QuestionColumns.GOODANSWER },
                        null, null, null, null, QuestionTable.QuestionColumns._ID, null);
        if (c.moveToFirst()) {
            do {
                Question question = this.buildQuestionFromCursor(c);
                if (question != null) {
                    list.add(question);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }

    private Question buildQuestionFromCursor(Cursor c) {
        Question question = null;
        if (c != null) {
            question = new Question(c.getString(1), c.getString(2),
            c.getString(3), c.getString(4), c.getString(5));
            question.setId(c.getLong(0));
            question.setImageName(c.getString(1));
            question.setWrongAnswer1(c.getString(2));
            question.setWrongAnswer2(c.getString(3));
            question.setWrongAnswer3(c.getString(4));
            question.setGoodAnswer(c.getString(5));
        }
        return question;
    }
}
