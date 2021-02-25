package com.borelanjo.jobservice.application.producer;

import com.borelanjo.jobservice.domain.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobProducer {

    private final JobService jobService;

    public void send(String type) {
        jobService.create(null);
    }
}
