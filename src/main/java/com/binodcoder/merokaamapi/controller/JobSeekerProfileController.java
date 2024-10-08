package com.binodcoder.merokaamapi.controller;

import com.binodcoder.merokaamapi.entity.JobSeekerProfile;
import com.binodcoder.merokaamapi.entity.Users;
import com.binodcoder.merokaamapi.exceptions.ResourceNotFoundException;
import com.binodcoder.merokaamapi.service.JobSeekerProfileService;
import com.binodcoder.merokaamapi.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/job-profile")
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
        Users users = authUtil.loggedInUser();
        return jobSeekerProfileService.addNew(jobSeekerProfile, users);
    }

    @GetMapping("/profile")
    public ResponseEntity<List<JobSeekerProfile>> getAll() {
        List<JobSeekerProfile> jobSeekerProfiles = jobSeekerProfileService.getAll();
        return new ResponseEntity<>(jobSeekerProfiles, HttpStatus.OK);
    }

    @GetMapping("/profile/edit/{id}")
    public ResponseEntity<JobSeekerProfile> edit(@PathVariable("id") Long id) {
        Optional<JobSeekerProfile> optionalProfile = jobSeekerProfileService.getOne(id);
        return new ResponseEntity<>(optionalProfile.get(), HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<JobSeekerProfile> getOne(@PathVariable("id") Long id) {
        Optional<JobSeekerProfile> optionalProfile = jobSeekerProfileService.getOne(id);
        // If the profile is not found, throw a ResourceNotFoundException
        JobSeekerProfile profile = optionalProfile.orElseThrow(() ->
                new ResourceNotFoundException("JobSeekerProfile", "id", id));
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<JobSeekerProfile> deleteJobProfile(@PathVariable("id") Long id) {
        jobSeekerProfileService.deleteJobProfile(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<JobSeekerProfile> updateJobProfile(@RequestBody JobSeekerProfile jobSeekerProfile, @PathVariable("id") Long id) {
        jobSeekerProfileService.updateJobProfile(jobSeekerProfile, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
