package com.borelanjo.jobservice.application.service;

import com.borelanjo.jobservice.domain.model.Job;
import com.borelanjo.jobservice.domain.service.JobService;
import com.borelanjo.jobservice.infrastructure.persistence.hibernate.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository repository;

    @Override
    public Job findById(Long id) {
        Optional<Job> optJob = repository.findById(id);
        if (!optJob.isPresent()) {
            throw new RuntimeException("Job inexistente");
        }
        return optJob.get();
    }

    @Override
    public Page<Job> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Job save(Job job) {
        return nonNull(job) ? repository.save(job) : null;
    }

    @Override
    public Job update(Long id, Job job) {
        Job savedJob = findById(id);
        return nonNull(savedJob) ? repository.save(savedJob) : null;
    }
}
