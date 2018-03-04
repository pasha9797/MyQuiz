package com.pavel.chernyshov.myquiz.model;

import java.util.List;

/**
 * Created by pasha on 04.03.2018.
 */

public class Question {

    private String text;
    private List<Answer> answers;

    public String getText() {
        return text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Question(String text, List<Answer> answers) {
        this.text = text;
        this.answers = answers;
    }
}
