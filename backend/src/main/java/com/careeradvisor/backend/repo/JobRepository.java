package com.careeradvisor.backend.repo;

import com.careeradvisor.backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCareerIgnoreCase(String career);
}
