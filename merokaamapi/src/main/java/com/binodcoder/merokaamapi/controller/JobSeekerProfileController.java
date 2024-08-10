package com.binodcoder.merokaamapi.controller;

import com.binodcoder.merokaamapi.entity.JobSeekerProfile;
import com.binodcoder.merokaamapi.entity.Users;
import com.binodcoder.merokaamapi.entity.UsersType;
import com.binodcoder.merokaamapi.service.JobSeekerProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class JobSeekerProfileController {

    private final JobSeekerProfileService jobSeekerProfileService;

    public JobSeekerProfileController(JobSeekerProfileService jobSeekerProfileService) {
        this.jobSeekerProfileService = jobSeekerProfileService;
    }

    @PostMapping("/create")
    public JobSeekerProfile create(@RequestBody JobSeekerProfile jobSeekerProfile) {
        jobSeekerProfile.setUserAccountId(0);
        jobSeekerProfile.setUserId(new Users(6, "..", "..", true, null, new UsersType(1, "", null)));
        return jobSeekerProfileService.addNew(jobSeekerProfile);
    }

    @GetMapping("/profile_only")
    public ResponseEntity<JobSeekerProfile> getOne() {
        Optional<JobSeekerProfile> optionalProfile = jobSeekerProfileService.getOne(5);
        return new ResponseEntity<>(optionalProfile.get(), HttpStatus.OK);
    }
}
