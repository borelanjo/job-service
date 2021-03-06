package com.borelanjo.jobservice.application.consumer;

import com.borelanjo.jobservice.domain.model.Job;
import com.borelanjo.jobservice.domain.service.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class JobMdb {

    private final JobService jobService;

    @KafkaListener(topics = "job-wait")
    public void receiveWait(@Payload Job data,
                                @Headers MessageHeaders headers) {

        Job createdJob = jobService.wait(data);
        log.info("waiting job='{}'", createdJob);

    }

    @KafkaListener(topics = "job-start")
    public void receiveStart(@Payload Job data,
                               @Headers MessageHeaders headers) {

        Job startJob = jobService.start(getCodeByKey(headers), data);
        log.info("started job='{}'", startJob);

    }

    @KafkaListener(topics = "job-success")
    public void receiveSuccess(@Payload Job data,
                            @Headers MessageHeaders headers) {

        Job successJob = jobService.success(getCodeByKey(headers), data);
        log.info("success job='{}'", successJob);

    }

    @KafkaListener(topics = "job-error")
    public void receiveError(@Payload Job data,
                               @Headers MessageHeaders headers) {

        Job errorJob = jobService.error(getCodeByKey(headers), data);
        log.info("error job='{}'", errorJob);

    }

    @KafkaListener(topics = "job-dead")
    public void receiveDead(@Payload Job data,
                                @Headers MessageHeaders headers) {

        Job updatedJob = jobService
                .dead(getCodeByKey(headers), data);
        log.info("dead job='{}'", updatedJob);
    }

    private UUID getCodeByKey(MessageHeaders headers) {
        return UUID.fromString(headers.get("kafka_receivedMessageKey", String.class));
    }
}
