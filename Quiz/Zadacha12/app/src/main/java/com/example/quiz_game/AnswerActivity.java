package com.example.quiz_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    private TextView textViewResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        textViewResults = findViewById(R.id.textViewResults);
        String[] userAnswers = getIntent().getStringArrayExtra("userAnswers");
        for (String userAnswer: userAnswers) {
            textViewResults.append(userAnswer + "\n");
        }
    }
}