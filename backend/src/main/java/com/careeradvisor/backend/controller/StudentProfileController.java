package com.careeradvisor.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.careeradvisor.backend.entity.Student;
import com.careeradvisor.backend.entity.User;
import com.careeradvisor.backend.repo.UserRepository;
import com.careeradvisor.backend.service.StudentService;


@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class StudentProfileController {

    @Autowired
    private StudentService studentProfileService;

    @Autowired
    private UserRepository userRepository;

    // ✅ Save Student Profile
    @PostMapping("/{userId}")
    public Student createProfile(@PathVariable Long userId,
                                        @RequestBody Student profile) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        profile.setUser(user);

        return studentProfileService.saveProfile(profile);
    }

    // ✅ Get Student Profile
    @GetMapping("/{userId}")
    public Student getProfile(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return studentProfileService.getProfileByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }
}