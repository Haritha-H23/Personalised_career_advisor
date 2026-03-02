package com.careeradvisor.backend.service;

import com.careeradvisor.backend.entity.UserAnswer;
import com.careeradvisor.backend.repo.UserAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;

    public UserAnswerService(UserAnswerRepository userAnswerRepository) {
        this.userAnswerRepository = userAnswerRepository;
    }

    public List<UserAnswer> saveAllAnswers(List<UserAnswer> answers) {
        return userAnswerRepository.saveAll(answers);
    }

    public List<UserAnswer> getAnswersByUser(Long userId) {
        return userAnswerRepository.findByUserId(userId);
    }
}