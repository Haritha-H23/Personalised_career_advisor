package com.careeradvisor.backend.controller;

import com.careeradvisor.backend.entity.AssessmentScore;
import com.careeradvisor.backend.entity.UserAnswer;
import com.careeradvisor.backend.repo.UserAnswerRepository;
import com.careeradvisor.backend.service.AssessmentScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessment")
@CrossOrigin
public class AssessmentController {

    private final AssessmentScoreService assessmentScoreService;
    private final UserAnswerRepository userAnswerRepository;

    public AssessmentController(AssessmentScoreService assessmentScoreService,
                                UserAnswerRepository userAnswerRepository) {
        this.assessmentScoreService = assessmentScoreService;
        this.userAnswerRepository = userAnswerRepository;
    }

    // ✅ Submit Assessment
    @PostMapping("/submit/{userId}")
    public AssessmentScore submitAssessment(
            @PathVariable Long userId,
            @RequestBody List<UserAnswer> answers) {

        // Save user answers first
        userAnswerRepository.saveAll(answers);

        // Calculate score
        return assessmentScoreService.calculateScore(userId, answers);
    }

    // ✅ Get Result by User
    @GetMapping("/result/{userId}")
    public AssessmentScore getResult(@PathVariable Long userId) {
        return assessmentScoreService.getScoreByUser(userId);
    }
}