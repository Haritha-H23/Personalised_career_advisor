package com.careeradvisor.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CareerResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(columnDefinition = "TEXT")
    private String resultJson; // stores full prediction JSON as string
}
