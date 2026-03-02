package com.careeradvisor.backend.controller;

import com.careeradvisor.backend.entity.UserAnswer;
import com.careeradvisor.backend.service.UserAnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessment")
@CrossOrigin
public class UserAnswerController {

    private final UserAnswerService userAnswerService;

    public UserAnswerController(UserAnswerService userAnswerService) {
        this.userAnswerService = userAnswerService;
    }

    @PostMapping("/answers")
    public List<UserAnswer> submitAnswers(@RequestBody List<UserAnswer> answers) {
        return userAnswerService.saveAllAnswers(answers);
    }
}