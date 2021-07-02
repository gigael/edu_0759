package com.example.quiz_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnYes;
    private Button btnNo;
    private TextView textViewQuestion;
    private com.example.quiz_game.Question[] questions = {
            new com.example.quiz_game.Question(R.string.question0, false),
            new com.example.quiz_game.Question(R.string.question1, true),
            new com.example.quiz_game.Question(R.string.question2, true),
            new com.example.quiz_game.Question(R.string.question3, false),
            new com.example.quiz_game.Question(R.string.question4, false),
    };
    private int questionIndex = 0;
    private String[] userAnswers = new String[questions.length + 1];
    private int userCorrectAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt("CurrentIndex", 0);
        }

        btnYes = findViewById(R.id.buttonYes);
        btnNo = findViewById(R.id.buttonNo);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewQuestion.setText(questions[questionIndex].getQuestion());

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questions[questionIndex].isAnswer()){
                    Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
                    userCorrectAnswers++;
                } else
                    Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                userAnswers[questionIndex] = String.format( "Вопрос %d: %s.\nВаш ответ - Да. Правильный ответ - %s\n",
                        questionIndex + 1,
                        getResources().getString(questions[questionIndex].getQuestion()),
                        transformAnswer(questions[questionIndex].isAnswer()));
                questionIndex += 1;
                setQuestionByIndex(questionIndex);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!questions[questionIndex].isAnswer()){
                    Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
                    userCorrectAnswers++;
                } else
                    Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                userAnswers[questionIndex] = String.format( "Вопрос %d: %s.\nВаш ответ - Нет. Правильный ответ - %s\n",
                        questionIndex + 1,
                        getResources().getString(questions[questionIndex].getQuestion()),
                        transformAnswer(questions[questionIndex].isAnswer()));
                questionIndex += 1;
                setQuestionByIndex(questionIndex);
            }
        });
    }

    public void setQuestionByIndex(int questionIndex) {
        if (questionIndex < questions.length) {
            textViewQuestion.setText(questions[questionIndex].getQuestion());
        } else results();;
    }

    public void results() {
        Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
        userAnswers[questionIndex] = "\nПравильных ответов: " + userCorrectAnswers + "/" + questions.length;
        intent.putExtra("userAnswers", userAnswers);
        intent.putExtra("userCorrectAnswers", userCorrectAnswers);
        intent.putExtra("questions", questions.length);
        startActivity(intent);
    }

    public String transformAnswer(Boolean isAnswer) {
        return (isAnswer?"Да":"Нет");
        }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("CurrentIndex", questionIndex);
    }
}