package com.careeradvisor.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String qualification;
    private String interest;
    private String skills;
    private int marks;
}
