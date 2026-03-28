package com.careeradvisor.backend.repo;

import com.careeradvisor.backend.entity.AlumniTalk;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlumniTalkRepository extends JpaRepository<AlumniTalk, Long> {
    List<AlumniTalk> findByCareerIgnoreCase(String career);
}
