package com.careeradvisor.backend.repo;

import com.careeradvisor.backend.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    List<Certification> findByCareerIgnoreCase(String career);
}
