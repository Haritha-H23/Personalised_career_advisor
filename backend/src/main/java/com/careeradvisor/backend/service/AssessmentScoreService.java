package com.careeradvisor.backend.service;

import com.careeradvisor.backend.entity.*;
import com.careeradvisor.backend.repo.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssessmentScoreService {

    private final QuestionRepository questionRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final AssessmentScoreRepository assessmentScoreRepository;

    public AssessmentScoreService(QuestionRepository questionRepository,
                                  UserAnswerRepository userAnswerRepository,
                                  AssessmentScoreRepository assessmentScoreRepository) {
        this.questionRepository = questionRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.assessmentScoreRepository = assessmentScoreRepository;
    }

    public AssessmentScore calculateScore(Long userId, List<UserAnswer> answers) {

        Map<String, Integer> categoryScore = new HashMap<>();
        Map<String, Integer> interestScore = new HashMap<>();

        for (UserAnswer ans : answers) {

            Question question = questionRepository
                    .findById(ans.getQuestionId())
                    .orElseThrow();

            if (question.getType().equals("APTITUDE")) {

                if (question.getCorrectAnswer()
                        .equals(ans.getSelectedAnswer())) {

                    categoryScore.put(
                            question.getCategory(),
                            categoryScore.getOrDefault(
                                    question.getCategory(), 0) + 1
                    );
                }

            } else if (question.getType().equals("INTEREST")) {

                String selected = ans.getSelectedAnswer();
                String domain = null;

                if (selected.equals("A")) domain = question.getOptionAMap();
                if (selected.equals("B")) domain = question.getOptionBMap();
                if (selected.equals("C")) domain = question.getOptionCMap();
                if (selected.equals("D")) domain = question.getOptionDMap();

                interestScore.put(
                        domain,
                        interestScore.getOrDefault(domain, 0) + 1
                );
            }
        }

        AssessmentScore score = new AssessmentScore();
        score.setUserId(userId);

        // Aptitude scores
        score.setLogicalScore(categoryScore.getOrDefault("LOGICAL", 0));
        score.setNumericalScore(categoryScore.getOrDefault("NUMERICAL", 0));
        score.setVerbalScore(categoryScore.getOrDefault("VERBAL", 0));
        score.setAnalyticalScore(categoryScore.getOrDefault("ANALYTICAL", 0));
        score.setProblemSolvingScore(categoryScore.getOrDefault("PROBLEM", 0));

        // Interest scores
        score.setTechInterest(interestScore.getOrDefault("TECHNOLOGY", 0));
        score.setCreativeInterest(interestScore.getOrDefault("CREATIVE", 0));
        score.setBusinessInterest(interestScore.getOrDefault("BUSINESS", 0));
        score.setHealthcareInterest(interestScore.getOrDefault("HEALTHCARE", 0));
        score.setResearchInterest(interestScore.getOrDefault("RESEARCH", 0));
        score.setSocialInterest(interestScore.getOrDefault("SOCIAL_SERVICE", 0));

        return assessmentScoreRepository.save(score);
    }

    public AssessmentScore getScoreByUser(Long userId) {
        return assessmentScoreRepository.findByUserId(userId).orElseThrow();
    }
}