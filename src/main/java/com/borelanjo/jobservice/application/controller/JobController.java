package com.borelanjo.jobservice.application.controller;

import com.borelanjo.jobservice.application.service.ResponseService;
import com.borelanjo.jobservice.domain.model.Job;
import com.borelanjo.jobservice.domain.service.JobService;
import com.borelanjo.jobservice.presentation.dto.job.JobRequestTo;
import com.borelanjo.jobservice.presentation.dto.job.JobResponseTo;
import com.borelanjo.jobservice.presentation.dto.shared.ResponseTo;
import com.borelanjo.jobservice.presentation.mapper.JobMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.borelanjo.jobservice.presentation.mapper.JobMapper.from;

@RestController
@RequestMapping("/api/jobs")
@AllArgsConstructor
public class JobController {

    private final JobService jobService;
    private final ResponseService responseService;


    @GetMapping("/{id}")
    public ResponseEntity<ResponseTo<JobResponseTo>> find(@PathVariable Long id) {
        return responseService.ok(from(jobService.findById((id))));
    }

    @GetMapping
    public ResponseEntity<ResponseTo<Page<JobResponseTo>>> findAll(Pageable pageable) {
        return responseService.ok(from(jobService.findAll(pageable)));
    }

    @PostMapping
    public ResponseEntity<ResponseTo<JobResponseTo>> save(@RequestBody JobRequestTo requestTO) {
        Job job = from(requestTO);
        return responseService.ok(from(jobService.save(job)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTo<JobResponseTo>> update(@PathVariable Long id,
                                                            @RequestBody JobRequestTo requestTO) {
        Job job = from(requestTO);
        return responseService.ok(from(jobService.update(id, job)));
    }

}
