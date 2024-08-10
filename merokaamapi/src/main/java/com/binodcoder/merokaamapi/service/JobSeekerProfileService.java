package com.binodcoder.merokaamapi.service;

import com.binodcoder.merokaamapi.entity.JobSeekerProfile;
import com.binodcoder.merokaamapi.repository.JobSeekerProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobSeekerProfileService {
    private final JobSeekerProfileRepository jobSeekerProfileRepository;

    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
    }

    public Optional<JobSeekerProfile> getOne(Integer id) {
        return jobSeekerProfileRepository.findById(id);
    }
}
