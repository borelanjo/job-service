package com.borelanjo.jobservice.presentation.mapper;

import com.borelanjo.jobservice.domain.model.Job;
import com.borelanjo.jobservice.presentation.dto.job.JobRequestTo;
import com.borelanjo.jobservice.presentation.dto.job.JobResponseTo;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.borelanjo.jobservice.domain.model.Status.WAITING;

public final class JobMapper {

    public static Job from(JobRequestTo requestTo) {
        return Job
                .builder()
                .code(UUID.randomUUID())
                .type(requestTo.getType())
                .status(WAITING)
                .description(requestTo.getDescription())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static JobResponseTo from(Job job) {
        return JobResponseTo
                .builder()
                .id(job.getId())
                .code(job.getCode())
                .type(job.getType())
                .status(job.getStatus())
                .description(job.getDescription())
                .startedAt(job.getStartedAt())
                .endedAt(job.getEndedAt())
                .build();
    }

    public static Page<JobResponseTo> from(Page<Job> jobs) {
        return jobs.map(job -> from(job));
    }
}
