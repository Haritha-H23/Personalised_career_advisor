package com.careeradvisor.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String career;
    private String title;
    private String issuer;
    private String url;
}
