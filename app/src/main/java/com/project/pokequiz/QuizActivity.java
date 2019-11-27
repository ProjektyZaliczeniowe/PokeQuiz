package com.project.pokequiz;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class QuizActivity extends AppCompatActivity {

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Objects.requireNonNull(getSupportActionBar()).hide();

        final Button quizMainButton = this.findViewById(R.id.mainButton);

        SQLiteOpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        QuestionDao questionDao = new QuestionDao(db);
        final List<Question> questionList = questionDao.getAll();
        Collections.shuffle(questionList);

        score = 0;

        Fragment fragment = QuestionFragment.newInstance(questionList.get(score));
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.question_frag, fragment).commit();
        quizMainButton.setEnabled(false);

        quizMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score >= questionList.size()){
                    TextView gameOverButton = findViewById(R.id.gameOverText);
                    gameOverButton.setText(getString(R.string.win));
                    gameOverButton.setVisibility(View.VISIBLE);
                    quizMainButton.setEnabled(false);
                }
                else
                    {
                        Fragment fragment = QuestionFragment.newInstance(questionList.get(score));
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.question_frag, fragment).commit();
                        quizMainButton.setEnabled(false);
                    }
            }
        });
    }
}
