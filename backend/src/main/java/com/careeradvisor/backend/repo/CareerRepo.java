package com.careeradvisor.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.careeradvisor.backend.entity.Career;
import java.util.List;

public interface CareerRepo extends JpaRepository<Career, Long> {

    List<Career> findByCategory(String category);

    List<Career> findByRequiredSkillsContaining(String skill);
}
