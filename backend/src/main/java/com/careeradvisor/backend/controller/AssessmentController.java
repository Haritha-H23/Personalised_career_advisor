package com.careeradvisor.backend.controller;

import com.careeradvisor.backend.entity.AssessmentScore;
import com.careeradvisor.backend.entity.UserAnswer;
import com.careeradvisor.backend.repo.UserAnswerRepository;
import com.careeradvisor.backend.service.AssessmentScoreService;
import com.careeradvisor.backend.service.MLService;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/assessment")
@CrossOrigin
public class AssessmentController {

    private final AssessmentScoreService assessmentScoreService;
    private final UserAnswerRepository userAnswerRepository;
    private final MLService mlService;   // ✅ ADD THIS

    public AssessmentController(AssessmentScoreService assessmentScoreService,
                                UserAnswerRepository userAnswerRepository,
                                MLService mlService) {
        this.assessmentScoreService = assessmentScoreService;
        this.userAnswerRepository = userAnswerRepository;
        this.mlService = mlService;   // ✅ ADD THIS
    }

    // ✅ Submit Assessment + Prediction
    @PostMapping("/submit/{userId}")
    public Map<String, Object> submitAssessment(
            @PathVariable Long userId,
            @RequestBody List<UserAnswer> answers) {

        // 1. Save answers
        userAnswerRepository.saveAll(answers);

        // 2. Calculate score
        AssessmentScore score = assessmentScoreService.calculateScore(userId, answers);

        // 3. Call ML API
        Map<String, Object> prediction = mlService.predictCareer(score);

        // 4. Combine response
        Map<String, Object> response = new HashMap<>();
        response.put("score", score);
        response.put("prediction", prediction);

        return response;
    }

    // Existing method (unchanged)
    @GetMapping("/result/{userId}")
    public AssessmentScore getResult(@PathVariable Long userId) {
        return assessmentScoreService.getScoreByUser(userId);
    }
}