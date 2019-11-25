package com.project.pokequiz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Button btnA = this.findViewById(R.id.answerA);
        Button btnB = this.findViewById(R.id.answerB);
        Button btnC = this.findViewById(R.id.answerC);
        Button btnD = this.findViewById(R.id.answerD);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = QuestionFragment.newInstance("A", "B");
                Bundle bundle = new Bundle();
                bundle.putString("question", "Pytanie A");
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.question_frag, fragment).commit();
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = QuestionFragment.newInstance("A", "B"); // TODO constructor for change,try only if works but not
                Bundle bundle = new Bundle();
                bundle.putString("question", "Pytanie B");
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.question_frag, fragment).commit();
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new QuestionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("question", "Pytanie C");
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.question_frag, fragment).commit();
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new QuestionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("question", "Pytanie D");
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.question_frag, fragment).commit();
            }
        });
    }
}
