package com.borelanjo.jobservice.application.producer;

import com.borelanjo.jobservice.domain.model.Job;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class JobProducer {

    private final KafkaTemplate kafkaTemplate;

    public void sendToWait(Job job) {
        kafkaTemplate.send("job-wait", job.getCode().toString(), job);
    }

    public void sendToDeath(UUID code, Job job) {
        kafkaTemplate.send("job-dead", code.toString(), job);
    }

    public void sendToDeath(Set<Job> jobs) {
        jobs.forEach(job -> sendToDeath(job.getCode(), job));
    }
}
