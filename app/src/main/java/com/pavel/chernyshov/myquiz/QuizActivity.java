package com.pavel.chernyshov.myquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pavel.chernyshov.myquiz.model.Question;
import com.pavel.chernyshov.myquiz.model.QuizContext;

import java.io.InputStream;

public class QuizActivity extends AppCompatActivity {
    private Button option1, option2;
    private TextView questionText, scoreText;
    private QuizContext quizContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        option1 = findViewById(R.id.button1);
        option2 = findViewById(R.id.button2);
        questionText = findViewById(R.id.question_text);
        scoreText = findViewById(R.id.score_text);

        loadQuizContext();

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizContext.ApplyNextAnswer(0);
                updateView();
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizContext.ApplyNextAnswer(1);
                updateView();
            }
        });


    }

    private void loadQuizContext() {

        InputStream ins = getResources().openRawResource(
                getResources().getIdentifier("questions",
                        "raw", getPackageName()));

        quizContext = new QuizContext(ins);

        updateView();
    }

    private void updateView() {
        if (quizContext.isFinished()) {
            quizContext.reset();
        }

        Question question = quizContext.getCurrentQuestion();
        questionText.setText(question.getText());
        option1.setText(question.getAnswers().get(0).getText());
        option2.setText(question.getAnswers().get(1).getText());
        scoreText.setText(quizContext.getScore() + "");
    }


}
