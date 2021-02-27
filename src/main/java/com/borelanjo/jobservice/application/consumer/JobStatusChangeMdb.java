package com.borelanjo.jobservice.application.consumer;

import com.borelanjo.jobservice.domain.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobStatusChangeMdb {

    private final JobService jobService;

    public void consume(String message) {
        jobService.update(null, null);
    }
}
