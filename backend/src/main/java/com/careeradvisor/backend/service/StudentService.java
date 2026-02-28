package com.careeradvisor.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.careeradvisor.backend.entity.Student;
import com.careeradvisor.backend.entity.User;
import com.careeradvisor.backend.repo.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentProfileRepository;

    public Student saveProfile(Student profile) {
        return studentProfileRepository.save(profile);
    }

    public Optional<Student> getProfileByUser(User user) {
        return studentProfileRepository.findByUser(user);
    }
}