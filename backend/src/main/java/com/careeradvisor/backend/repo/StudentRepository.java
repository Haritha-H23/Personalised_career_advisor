package com.careeradvisor.backend.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.careeradvisor.backend.entity.Student;
import com.careeradvisor.backend.entity.User;

public interface StudentRepository extends JpaRepository<Student, Long> {

     Optional<Student> findByUser(User user);
}