package com.borelanjo.jobservice.domain.service;

import com.borelanjo.jobservice.domain.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService {

    public Job findById(Long id);
    public Page<Job> findAll(Pageable pageable);
    public Job save(Job job);
    public Job update(Long id, Job job);

}
