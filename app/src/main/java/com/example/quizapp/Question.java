package com.example.quizapp;

public class Question {
    String question;
    String[] answers;
    int correct;

    public Question(String question, String[] answers, int correct) {
        this.question = question;
        this.answers = answers;
        this.correct = correct;
    }
}