package com.careeradvisor.backend.controller;

import com.careeradvisor.backend.entity.*;
import com.careeradvisor.backend.repo.*;
import com.careeradvisor.backend.service.LiveDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recommend")
@CrossOrigin
public class RecomendationController {

    private final CourseRepository        courseRepo;
    private final CertificationRepository certRepo;
    private final JobRepository           jobRepo;
    private final EventRepository         eventRepo;
    private final AlumniTalkRepository    alumniRepo;
    private final LiveDataService         liveData;

    public RecomendationController(CourseRepository courseRepo,
                                   CertificationRepository certRepo,
                                   JobRepository jobRepo,
                                   EventRepository eventRepo,
                                   AlumniTalkRepository alumniRepo,
                                   LiveDataService liveData) {
        this.courseRepo = courseRepo;
        this.certRepo   = certRepo;
        this.jobRepo    = jobRepo;
        this.eventRepo  = eventRepo;
        this.alumniRepo = alumniRepo;
        this.liveData   = liveData;
    }

    @GetMapping("/courses")
    public Object getCourses(@RequestParam String career) {
        List<Map<String, String>> live = liveData.fetchCourses(career);
        if (!live.isEmpty()) return live;
        return courseRepo.findByCareerIgnoreCase(career);
    }

    @GetMapping("/certifications")
    public Object getCertifications(@RequestParam String career) {
        List<Map<String, String>> live = liveData.fetchCertifications(career);
        if (!live.isEmpty()) return live;
        return certRepo.findByCareerIgnoreCase(career);
    }

    @GetMapping("/jobs")
    public Object getJobs(@RequestParam String career) {
        List<Map<String, String>> live = liveData.fetchJobs(career);
        if (!live.isEmpty()) return live;
        return jobRepo.findByCareerIgnoreCase(career);
    }

    @GetMapping("/events")
    public Object getEvents(@RequestParam String career) {
        List<Map<String, String>> live = liveData.fetchHackathons(career);
        if (!live.isEmpty()) return live;
        return eventRepo.findByCareerIgnoreCase(career);
    }

    @GetMapping("/alumni")
    public Object getAlumni(@RequestParam String career) {
        List<Map<String, String>> live = liveData.fetchAlumniTalks(career);
        if (!live.isEmpty()) return live;
        return alumniRepo.findByCareerIgnoreCase(career);
    }
}
