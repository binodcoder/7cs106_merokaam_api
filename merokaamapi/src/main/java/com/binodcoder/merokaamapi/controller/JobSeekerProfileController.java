package com.binodcoder.merokaamapi.controller;

import com.binodcoder.merokaamapi.entity.JobSeekerProfile;
import com.binodcoder.merokaamapi.entity.Users;
import com.binodcoder.merokaamapi.service.JobSeekerProfileService;
import com.binodcoder.merokaamapi.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final AuthUtil authUtil;

    private final JobSeekerProfileService jobSeekerProfileService;

    @Autowired
    public JobSeekerProfileController(AuthUtil authUtil, JobSeekerProfileService jobSeekerProfileService) {
        this.authUtil = authUtil;
        this.jobSeekerProfileService = jobSeekerProfileService;
    }

    @PostMapping("/create")
    public JobSeekerProfile create(@RequestBody JobSeekerProfile jobSeekerProfile) {
        jobSeekerProfile.setUserAccountId(0);
        Users users = authUtil.loggedInUser();
        return jobSeekerProfileService.addNew(jobSeekerProfile, users);
    }

    @GetMapping("/profile_only")
    public ResponseEntity<JobSeekerProfile> getOne() {
        Optional<JobSeekerProfile> optionalProfile = jobSeekerProfileService.getOne(5);
        return new ResponseEntity<>(optionalProfile.get(), HttpStatus.OK);
    }
}
