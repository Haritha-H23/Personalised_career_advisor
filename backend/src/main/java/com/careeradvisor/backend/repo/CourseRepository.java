package com.careeradvisor.backend.repo;

import com.careeradvisor.backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCareerIgnoreCase(String career);
}
