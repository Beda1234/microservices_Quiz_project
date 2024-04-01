package com.quiz.service;

import com.quiz.entity.Quiz;

import java.util.List;

public interface QuizService {
    Quiz addQuiz(Quiz quiz);

    void deleteQuiz(int id);

    Quiz getQuizById(int id);

    List<Quiz> getAllQuizzies();

    Quiz updateQuizById(String id, Quiz quiz);
}
