package com.careeradvisor.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stage;
    private String interests;
    private String workStyle;
    private String careerGoal;
    private String qualification;
    private String experience;

    private int communicationSkill;
    private int technicalSkill;
    private int problemSolvingSkill;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}