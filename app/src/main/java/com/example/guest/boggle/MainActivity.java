package com.example.guest.boggle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.allLetters) TextView mAllLetters;
    @Bind(R.id.submitButton) Button mSubmitButton;
    @Bind(R.id.answerPlace) EditText mAnswerPlace;
    @Bind(R.id.listView) ListView mListView;

    ArrayList<String> answers = new ArrayList<String>();

    Random r = new Random();

    private String[] consonants = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","y","z"};
    private String[] vowels = {"a", "e", "i", "o", "u"};

    private String[] choiceLetters = new String[] {vowels[r.nextInt(5)] ,  vowels[r.nextInt(5)],
            consonants[r.nextInt(20)] , consonants[r.nextInt(20)] ,  consonants[r.nextInt(20)] ,
            consonants[r.nextInt(20)] , consonants[r.nextInt(20)] , consonants[r.nextInt(20)]};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAllLetters.setText(Arrays.toString(choiceLetters));

       mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mSubmitButton) {

            int counter = 0;
            String answer = mAnswerPlace.getText().toString();

            for(int i = 0; i < answer.length() ; i ++){

                for(int j = 0; j < choiceLetters.length; j++){
                    if(answer.charAt(i) == choiceLetters[j].charAt(0) ){
                        counter += 1;
                        break;
                    }
                }
            }

            if(counter > 2) {
                answers.add(answer);
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, answers);
                mListView.setAdapter(adapter);

                choiceLetters = new String[]{vowels[r.nextInt(5)], vowels[r.nextInt(5)],
                        consonants[r.nextInt(20)], consonants[r.nextInt(20)], consonants[r.nextInt(20)],
                        consonants[r.nextInt(20)], consonants[r.nextInt(20)], consonants[r.nextInt(20)]};
                mAllLetters.setText(Arrays.toString(choiceLetters));
            }
        }
    }

}
