package com.careeradvisor.backend.controller;

import com.careeradvisor.backend.entity.AssessmentScore;
import com.careeradvisor.backend.entity.CareerResult;
import com.careeradvisor.backend.entity.UserAnswer;
import com.careeradvisor.backend.repo.CareerResultRepository;
import com.careeradvisor.backend.repo.UserAnswerRepository;
import com.careeradvisor.backend.service.AssessmentScoreService;
import com.careeradvisor.backend.service.MLService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assessment")
@CrossOrigin
public class AssessmentController {

    private final AssessmentScoreService  assessmentScoreService;
    private final UserAnswerRepository    userAnswerRepository;
    private final MLService               mlService;
    private final CareerResultRepository  careerResultRepo;
    private final ObjectMapper            objectMapper = new ObjectMapper();

    public AssessmentController(AssessmentScoreService assessmentScoreService,
                                UserAnswerRepository userAnswerRepository,
                                MLService mlService,
                                CareerResultRepository careerResultRepo) {
        this.assessmentScoreService = assessmentScoreService;
        this.userAnswerRepository   = userAnswerRepository;
        this.mlService              = mlService;
        this.careerResultRepo       = careerResultRepo;
    }

    @PostMapping("/submit/{userId}")
    public Map<String, Object> submitAssessment(
            @PathVariable Long userId,
            @RequestBody List<UserAnswer> answers) throws Exception {

        userAnswerRepository.saveAll(answers);
        AssessmentScore score = assessmentScoreService.calculateScore(userId, answers);
        Map<String, Object> prediction = mlService.predictCareer(score);

        // Save full result to DB
        String resultJson = objectMapper.writeValueAsString(
                Map.of("score", score, "prediction", prediction)
        );
        CareerResult careerResult = careerResultRepo.findByUserId(userId)
                .orElse(new CareerResult());
        careerResult.setUserId(userId);
        careerResult.setResultJson(resultJson);
        careerResultRepo.save(careerResult);

        Map<String, Object> response = new HashMap<>();
        response.put("score", score);
        response.put("prediction", prediction);
        return response;
    }

    // Frontend calls this on login to restore result
    @GetMapping("/career-result/{userId}")
    public Map<String, Object> getCareerResult(@PathVariable Long userId) throws Exception {
        return careerResultRepo.findByUserId(userId)
                .map(r -> {
                    try {
                        return objectMapper.readValue(r.getResultJson(), Map.class);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .orElse(null);
    }

    @GetMapping("/result/{userId}")
    public AssessmentScore getResult(@PathVariable Long userId) {
        return assessmentScoreService.getScoreByUser(userId);
    }
}
