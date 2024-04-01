package com.quiz.service;
import com.quiz.entity.QuestionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * There is another service which is working in this URL and value should be anything
 */
@FeignClient(url = "http://localhost:8082",value = "Question-Client")
public interface QuestionClient {
    /**
     * This Api is already present in the Question service controller class
     * From there we will collect the data by the help of FeignClient
     * @param quizId
     * @return
     */
    @GetMapping("/question/quiz/{quizId}")
    public List<QuestionDto> getQuestionOfQuiz(@PathVariable int quizId);
}
