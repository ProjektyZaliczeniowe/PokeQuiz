package com.project.pokequiz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {

    private Question question;
    private ImageView pokemon;
    private Button btn1;
    private Button btn2;
    private Button btn3;


    private OnFragmentInteractionListener mListener;

    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance() {
        QuestionFragment fragment = new QuestionFragment();
//        fragment.setQuestion(question);
        return fragment;
    }

    private void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        QuizActivity quizActivity = (QuizActivity) getActivity();
        question = quizActivity.currentQuestion;
        TextView scoreText = quizActivity.findViewById(R.id.scoreText);
        scoreText.setText("Twój wynik: "+ quizActivity.score);
        LayoutInflater lf = getActivity().getLayoutInflater();
        final View rootView = lf.inflate(R.layout.fragment_question, container, false);
        pokemon = rootView.findViewById(R.id.questionImage);
        int imageResource = getResources().getIdentifier("@drawable/"+question.getImageName(), "drawable", "com.project.pokequiz");
        pokemon.setImageResource(imageResource);
        btn1 = rootView.findViewById(R.id.ans1);
        btn1.setText(question.getWrongAnswer1());
        btn2 = rootView.findViewById(R.id.ans2);
        btn2.setText(question.getWrongAnswer2());
        btn3 = rootView.findViewById(R.id.ans3);
        btn3.setText(question.getWrongAnswer3());
        if(quizActivity.lockedFraggmentButtons) {
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            btn3.setEnabled(false);
            imageResource = getResources().getIdentifier("@drawable/"+question.getImageName()+"_color", "drawable", "com.project.pokequiz");
            pokemon.setImageResource(imageResource);
            TextView mainButton = quizActivity.findViewById(R.id.mainButton);
            mainButton.setEnabled(true);
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealPokemon();
                if(question.getGoodAnswer().equals(question.getWrongAnswer1())) {
                    //todo przechodzimy dalej
                    triggerGoodAnswer();
                } else {
                    //todo koniec gry
                    triggerWrongAnswer();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealPokemon();
                if(question.getGoodAnswer().equals(question.getWrongAnswer2())) {
                    //todo przechodzimy dalej
                    triggerGoodAnswer();
                } else {
                    //todo koniec gry
                    triggerWrongAnswer();
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealPokemon();
                if(question.getGoodAnswer().equals(question.getWrongAnswer3())) {
                    //todo przechodzimy dalej
                    triggerGoodAnswer();
                } else {
                    //todo koniec gry
                    triggerWrongAnswer();
                }
            }
        });
        return rootView;
    }


    private void triggerGoodAnswer() {
        QuizActivity quizActivity = (QuizActivity) getActivity();
        TextView mainButton = quizActivity.findViewById(R.id.mainButton);
        mainButton.setEnabled(true);
        quizActivity.score++;
        if(quizActivity.score < quizActivity.questionList.size()) {
            quizActivity.currentQuestion = quizActivity.questionList.get(quizActivity.score);
        }
        TextView scoreText = quizActivity.findViewById(R.id.scoreText);
        scoreText.setText("Twój wynik: "+ quizActivity.score);
    }

    private void triggerWrongAnswer() {
        QuizActivity quizActivity = (QuizActivity) getActivity();
        TextView gameOverText = quizActivity.findViewById(R.id.gameOverText);
        gameOverText.setVisibility(View.VISIBLE);
    }

    private void revealPokemon() {
        int imageResource = getResources().getIdentifier("@drawable/"+question.getImageName()+"_color", "drawable", "com.project.pokequiz");
        pokemon.setImageResource(imageResource);
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
