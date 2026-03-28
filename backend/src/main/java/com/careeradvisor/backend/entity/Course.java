package com.careeradvisor.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String career;
    private String title;
    private String platform;
    private String url;
    private String level; // Beginner, Intermediate, Advanced
}
