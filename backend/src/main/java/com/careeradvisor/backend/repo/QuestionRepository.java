package com.careeradvisor.backend.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.careeradvisor.backend.entity.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCategory(String category);
}