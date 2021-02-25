package com.borelanjo.jobservice.application.service;

import com.borelanjo.jobservice.domain.model.Job;
import com.borelanjo.jobservice.domain.service.JobService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class JobServiceImpl implements JobService {
    @Override
    public Optional<Job> find(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<Job> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Job create(Job job) {
        return job;
    }

    @Override
    public Job update(Long id, Job job) {
        return job;
    }
}
