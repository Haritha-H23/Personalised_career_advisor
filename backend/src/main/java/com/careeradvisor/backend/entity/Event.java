package com.careeradvisor.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String career;
    private String title;
    private String type; // Hackathon, Workshop, Conference
    private String date;
    private String url;
}
