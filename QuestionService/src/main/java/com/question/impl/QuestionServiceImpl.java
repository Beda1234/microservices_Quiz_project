package com.question.impl;

import com.question.service.QuestionService;
import com.question.entity.Question;
import com.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(()->new NoSuchElementException("Could not find"));
    }

    @Override
    public List<Question> findByQuizId(int id) {
        return questionRepository.findByQuizId(id);
    }

    @Override
    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }
}
