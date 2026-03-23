package com.careeradvisor.backend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.careeradvisor.backend.entity.AssessmentScore;

@Service
public class MLService {

    public Map<String, Object> predictCareer(AssessmentScore score) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://127.0.0.1:5000/predict";

        Map<String, Object> request = new HashMap<>();

        request.put("logical", score.getLogicalScore());
        request.put("numerical", score.getNumericalScore());
        request.put("verbal", score.getVerbalScore());
        request.put("analytical", score.getAnalyticalScore());
        request.put("problem", score.getProblemSolvingScore());

        request.put("tech", score.getTechInterest());
        request.put("creative", score.getCreativeInterest());
        request.put("business", score.getBusinessInterest());
        request.put("research", score.getResearchInterest());
        request.put("social", score.getSocialInterest());

        // 🔥 CALL ML API
        Map response = restTemplate.postForObject(url, request, Map.class);

        return response;
    }
}