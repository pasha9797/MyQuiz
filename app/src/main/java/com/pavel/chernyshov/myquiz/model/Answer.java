package com.pavel.chernyshov.myquiz.model;

/**
 * Created by pasha on 04.03.2018.
 */

public class Answer {
    private String text;
    private int score;

    public Answer(String text, int score) {
        this.text = text;
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public int getScore() {
        return score;
    }

}
