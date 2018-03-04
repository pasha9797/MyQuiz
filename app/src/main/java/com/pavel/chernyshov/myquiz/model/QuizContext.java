package com.pavel.chernyshov.myquiz.model;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by pasha on 04.03.2018.
 */

public class QuizContext {
    private List<Question> questions;

    private int score;
    private int currentQuestion;
    private boolean finished;

    public int ApplyNextAnswer(int id) {
        score += questions.get(currentQuestion).getAnswers().get(id).getScore();
        currentQuestion++;
        if (currentQuestion == questions.size()) {
            finished = true;
        }
        return score;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestion);
    }

    public boolean isFinished() {
        return finished;
    }

    public int getScore() {
        return score;
    }

    public QuizContext(InputStream inputStream) {
        reset();
        try {
            QuizContext temp = (new Gson()).fromJson(readFile(inputStream), QuizContext.class);
            this.questions = temp.questions;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        score = 0;
        currentQuestion = 0;
        finished = false;
    }

    private String readFile(InputStream inputStream) throws IOException {
        String result = null;
        try {
            // json is UTF-8 by default

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            result = sb.toString();

            return result;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (Exception squish) {
            }
        }
    }
}
