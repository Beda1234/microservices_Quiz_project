package com.quiz.controller;

import com.quiz.entity.Quiz;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping
    public Quiz create(@RequestBody Quiz quiz) {
        return quizService.addQuiz(quiz);
    }

    @GetMapping
    public List<Quiz> get() {
        return quizService.getAllQuizzies();
    }

    @GetMapping("/{id}")
    public Quiz getOne(@PathVariable int id) {
        return quizService.getQuizById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable int id) {
        quizService.deleteQuiz(id);
        return "Deleted successfully";
    }
}
