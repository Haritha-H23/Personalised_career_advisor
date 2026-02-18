package com.careeradvisor.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;              // Software Engineer, Data Analyst etc

    @Column(length = 1000)
    private String description;        // Career description

    private String category;           // IT, Core, Government, Design etc

    private String requiredSkills;     // Java, SQL, Communication etc

    private String averageSalary;      // 6 LPA, 10 LPA etc

    private String growthRate;         // High, Medium, Low

}
