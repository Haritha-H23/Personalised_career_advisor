package com.careeradvisor.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careeradvisor.backend.entity.StudentProfile;
import com.careeradvisor.backend.repo.ProfileRepo;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileRepo profileRepository;

    public ProfileController(ProfileRepo profileRepository) {
        this.profileRepository = profileRepository;
    }

    @PostMapping("/save")
    public StudentProfile saveProfile(@RequestBody StudentProfile profile) {
        return profileRepository.save(profile);
    }

    @GetMapping("/all")
    public List<StudentProfile> getAll() {
        return profileRepository.findAll();
    }
}
