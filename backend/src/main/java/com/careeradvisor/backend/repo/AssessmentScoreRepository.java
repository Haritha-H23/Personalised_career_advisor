package com.careeradvisor.backend.repo;

import com.careeradvisor.backend.entity.AssessmentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AssessmentScoreRepository extends JpaRepository<AssessmentScore, Long> {

    Optional<AssessmentScore> findByUserId(Long userId);
}