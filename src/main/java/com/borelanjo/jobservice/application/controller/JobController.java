package com.borelanjo.jobservice.application.controller;

import com.borelanjo.jobservice.application.producer.JobProducer;
import com.borelanjo.jobservice.application.service.ResponseService;
import com.borelanjo.jobservice.domain.model.Job;
import com.borelanjo.jobservice.domain.service.JobService;
import com.borelanjo.jobservice.presentation.dto.job.JobCreateRequestTo;
import com.borelanjo.jobservice.presentation.dto.job.JobResponseTo;
import com.borelanjo.jobservice.presentation.dto.job.JobUpdateRequestTo;
import com.borelanjo.jobservice.presentation.dto.shared.ResponseTo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.borelanjo.jobservice.presentation.mapper.JobMapper.from;

@RestController
@RequestMapping("/api/jobs")
@AllArgsConstructor
public class JobController {

    private final JobService jobService;
    private final JobProducer jobProducer;
    private final ResponseService responseService;

    @GetMapping("/{code}")
    public ResponseEntity<ResponseTo<JobResponseTo>> findByCode(@PathVariable UUID code) {
        return responseService.ok(from(jobService.findByCode((code))));
    }

    @GetMapping
    public ResponseEntity<ResponseTo<Page<JobResponseTo>>> findAll(Pageable pageable) {
        return responseService.ok(from(jobService.findAll(pageable)));
    }

    @PostMapping
    public ResponseEntity<ResponseTo<JobResponseTo>> wait(@RequestBody JobCreateRequestTo requestTO) {
        Job job = from(requestTO);

        jobProducer.sendToWait(job);

        return responseService.accepted(from(job));
    }

    @PatchMapping(value = "/{code}/status/DEAD", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseTo<JobResponseTo>> dead(@PathVariable UUID code,
                                                               @RequestBody JobUpdateRequestTo requestTO) {
        Job job = from(requestTO);

        jobProducer.sendToDeath(code, job);

        return responseService.accepted(from(job));
    }

}
