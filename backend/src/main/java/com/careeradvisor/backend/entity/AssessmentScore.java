package com.careeradvisor.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AssessmentScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private int logicalScore;
    private int numericalScore;
    private int verbalScore;
    private int analyticalScore;
    private int problemSolvingScore;

    private int techInterest;
    private int creativeInterest;
    private int businessInterest;
    private int healthcareInterest;
    private int researchInterest;
    private int socialInterest;
}