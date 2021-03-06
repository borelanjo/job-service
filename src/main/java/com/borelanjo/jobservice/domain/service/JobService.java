package com.borelanjo.jobservice.domain.service;

import com.borelanjo.jobservice.domain.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface JobService {

    public Job findById(Long id);
    public Job findByCode(UUID code);
    public Page<Job> findAll(Pageable pageable);
    public Job wait(Job job);
    public Job start(UUID code, Job job);
    public Job success(UUID code, Job job);
    public Job error(UUID code, Job job);
    public Job dead(UUID code, Job job);

}
