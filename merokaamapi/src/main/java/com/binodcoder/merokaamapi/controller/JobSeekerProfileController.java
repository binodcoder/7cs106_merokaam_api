package com.binodcoder.merokaamapi.controller;

import com.binodcoder.merokaamapi.entity.JobSeekerProfile;
import com.binodcoder.merokaamapi.service.JobSeekerProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class JobSeekerProfileController {

    private final JobSeekerProfileService jobSeekerProfileService;

    public JobSeekerProfileController(JobSeekerProfileService jobSeekerProfileService) {
        this.jobSeekerProfileService = jobSeekerProfileService;
    }

    @GetMapping("/profile_only")
    public ResponseEntity<JobSeekerProfile> getOne() {
        Optional<JobSeekerProfile> optionalProfile = jobSeekerProfileService.getOne(5);
        return new ResponseEntity<>(optionalProfile.get(), HttpStatus.OK);
    }
}
