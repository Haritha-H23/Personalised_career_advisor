package com.careeradvisor.backend.service;


import org.springframework.stereotype.Service;

import com.careeradvisor.backend.entity.Question;
import com.careeradvisor.backend.repo.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        List<Question> list = questionRepository.findAll();
        System.out.println("Total questions fetched: " + list.size());
        return list;
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
}