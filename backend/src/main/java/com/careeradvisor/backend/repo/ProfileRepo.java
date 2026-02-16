package com.careeradvisor.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.careeradvisor.backend.entity.StudentProfile;

public interface ProfileRepo extends JpaRepository<StudentProfile, Long> {
}
