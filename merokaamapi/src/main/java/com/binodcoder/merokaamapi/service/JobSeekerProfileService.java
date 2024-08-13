package com.binodcoder.merokaamapi.service;

import com.binodcoder.merokaamapi.entity.JobSeekerProfile;
import com.binodcoder.merokaamapi.entity.Users;
import com.binodcoder.merokaamapi.repository.JobSeekerProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerProfileService {
    private final JobSeekerProfileRepository jobSeekerProfileRepository;

    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
    }

    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile, Users users) {
        jobSeekerProfile.setUserId(users);
        return jobSeekerProfileRepository.save(jobSeekerProfile);
    }

    public Optional<JobSeekerProfile> getOne(Integer id) {
        return jobSeekerProfileRepository.findById(id);
    }

    public List<JobSeekerProfile> getAll() {
        return jobSeekerProfileRepository.findAll();
    }
}
