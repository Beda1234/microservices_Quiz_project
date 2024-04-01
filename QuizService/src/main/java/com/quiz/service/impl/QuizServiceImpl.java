package com.quiz.service.impl;

import com.quiz.entity.Quiz;
import com.quiz.repository.QuizRepository;
import com.quiz.service.QuestionClient;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionClient questionClient;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(int id) {
        quizRepository.deleteById(id);
    }

    @Override
    public Quiz getQuizById(int id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find"));
        quiz.setQuestionDtoList(questionClient.getQuestionOfQuiz(quiz.getId()));
        return quiz;
    }

    @Override
    public Quiz updateQuizById(String id, Quiz quiz) {
        return null;
    }

    @Override
    public List<Quiz> getAllQuizzies() {
        List<Quiz> all = quizRepository.findAll();
        List<Quiz> newQuizList = all.stream().map(quiz -> {
            quiz.setQuestionDtoList(questionClient.getQuestionOfQuiz(quiz.getId()));
            return quiz;
        }).collect(Collectors.toList());
        return newQuizList;
    }
}
