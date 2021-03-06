package com.borelanjo.jobservice.application.service;

import com.borelanjo.jobservice.domain.model.Job;
import com.borelanjo.jobservice.domain.model.Status;
import com.borelanjo.jobservice.domain.service.JobService;
import com.borelanjo.jobservice.infrastructure.persistence.hibernate.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.borelanjo.jobservice.domain.model.Status.RUNNING;
import static com.borelanjo.jobservice.domain.model.Status.WAITING;
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
    public Job findByCode(UUID code) {
        Optional<Job> optJob = repository.findByCode(code);
        if (!optJob.isPresent()) {
            throw new RuntimeException("Job ainda não processado ou inexistente");
        }
        return optJob.get();
    }

    @Override
    public Page<Job> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Job wait(Job job) {
        job.setStatus(WAITING);
        return nonNull(job) ? repository.save(job) : null;
    }

    @Override
    public Job start(UUID code, Job job) {
        Job savedJob = findByCode(code);
        if (nonNull(savedJob) && savedJob.isWaiting()) {
            savedJob.setStatus(RUNNING);
            savedJob.setStartedAt(nonNull(job.getStartedAt()) ? job.getStartedAt() : LocalDateTime.now());
            savedJob.setDescription(job.getDescription());
        }
        return update(savedJob);
    }

    @Override
    public Job success(UUID code, Job job) {
        Job savedJob = findByCode(code);
        if (nonNull(savedJob) && !savedJob.isEnded()) {
            savedJob.setStatus(Status.SUCCESS);
            savedJob.setEndedAt(LocalDateTime.now());
            savedJob.setDescription(job.getDescription());
        }

        return update(savedJob);
    }

    @Override
    public Job error(UUID code, Job job) {
        Job savedJob = findByCode(code);
        if (nonNull(savedJob) && !savedJob.isEnded()) {
            savedJob.setStatus(Status.ERROR);
            savedJob.setEndedAt(LocalDateTime.now());
            savedJob.setDescription(job.getDescription());
        }

        return update(savedJob);
    }

    @Override
    public Job dead(UUID code, Job job) {
        Job savedJob = findByCode(code);
        if (nonNull(savedJob) && !savedJob.isEnded()) {
            savedJob.setStatus(Status.DEAD);
            savedJob.setEndedAt(LocalDateTime.now());
            savedJob.setDescription(job.getDescription());
        }

        return update(savedJob);
    }

    private Job update(Job job) {
        return nonNull(job) ? repository.save(job) : null;
    }

}
