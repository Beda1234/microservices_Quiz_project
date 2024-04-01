package com.question.service;

import com.question.entity.Question;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question question);

    void deleteQuestion(Long id);

    Question getQuestionById(Long id);

    List<Question> findByQuizId(int id);

    List<Question> getAllQuestion();
}
