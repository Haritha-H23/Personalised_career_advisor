package com.careeradvisor.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category; // LOGICAL, NUMERICAL, INTEREST etc.
    private String type;     // APTITUDE or INTEREST

    private String questionText;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    // For aptitude
    private String correctAnswer;

    // For interest mapping
    private String optionAMap;
    private String optionBMap;
    private String optionCMap;
    private String optionDMap;
}