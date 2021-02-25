package com.borelanjo.jobservice.domain.service;

import com.borelanjo.jobservice.domain.model.Job;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface JobService {

    public Optional<Job> find(Long id);
    public Set<Job> findAll(Pageable pageable);
    public Job create(Job job);
    public Job update(Long id, Job job);

}
