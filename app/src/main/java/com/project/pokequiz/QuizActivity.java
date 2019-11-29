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

import java.util.List;
import java.util.Objects;

public class QuizActivity extends AppCompatActivity {

    int score;
    List<Question> questionList;
    Question currentQuestion;
    boolean lockedFraggmentButtons;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score", score);
        if(score < questionList.size()) {
            outState.putSerializable("currentQuestion", questionList.get(score));
            outState.putBoolean("locked", false);
        } else {
            outState.putSerializable("currentQuestion", currentQuestion);
            outState.putBoolean("locked", true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Objects.requireNonNull(getSupportActionBar()).hide();

        final Button quizMainButton = this.findViewById(R.id.mainButton);

//        SQLiteOpenHelper openHelper = new OpenHelper(this);
//        SQLiteDatabase db = openHelper.getWritableDatabase();
//        QuestionDao questionDao = new QuestionDao(db);
//        questionList = questionDao.getAll();
//        Collections.shuffle(questionList);
        questionList = (List<Question>) getIntent().getSerializableExtra("QuestionList");

        if(savedInstanceState != null) {
            score = savedInstanceState.getInt("score");
            currentQuestion = (Question) savedInstanceState.getSerializable("currentQuestion");
            lockedFraggmentButtons = savedInstanceState.getBoolean("locked");

        } else {
            score = 0;
            currentQuestion = questionList.get(score);
        }
        Fragment fragment = QuestionFragment.newInstance();
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
                        Fragment fragment = QuestionFragment.newInstance();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.question_frag, fragment).commit();
                        quizMainButton.setEnabled(false);
                    }
            }
        });
    }
}
