package com.borelanjo.jobservice.presentation.mapper;

import com.borelanjo.jobservice.domain.model.Job;
import com.borelanjo.jobservice.presentation.dto.job.JobCreateRequestTo;
import com.borelanjo.jobservice.presentation.dto.job.JobResponseTo;
import com.borelanjo.jobservice.presentation.dto.job.JobUpdateRequestTo;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.UUID;

public final class JobMapper {

    public static Job from(JobCreateRequestTo requestTo) {
        return Job
                .builder()
                .code(UUID.randomUUID())
                .type(requestTo.getType())
                .description(requestTo.getDescription())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Job from(JobUpdateRequestTo requestTo) {
        return Job
                .builder()
                .code(UUID.randomUUID())
                .description(requestTo.getDescription())
                .build();
    }

    public static JobResponseTo from(Job job) {
        return JobResponseTo
                .builder()
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
