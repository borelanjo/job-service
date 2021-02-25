package com.borelanjo.jobservice.domain.service;

import com.borelanjo.jobservice.application.service.JobServiceImpl;
import com.borelanjo.jobservice.domain.model.Job;
import com.borelanjo.jobservice.domain.model.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static com.borelanjo.jobservice.domain.model.Status.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class JobServiceTest {

    private final JobService jobService;

    @Autowired
    public JobServiceTest() {
        this.jobService = new JobServiceImpl();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void find() {
        Optional<Job> job = jobService.find(1L);

        assertTrue(job.isPresent());
    }

    @Test
    void findAll() {
        Set<Job> jobs = jobService.findAll(null);
        assertEquals(3, jobs.size());
    }

    @Test
    void create() {
        Job job = jobService.create(Job
                .builder()
                .startedAt(LocalDateTime.now())
                .description("teste")
                .status(Status.WAITING)
                .type("RANDOM_NUMBER")
                .build());

        assertNotNull(job);
        assertEquals(1L, job.getId());
    }

    @Test
    void update() {
        Job job = jobService.update(1L, Job
                .builder()
                .endedAt(LocalDateTime.now())
                .description("updated")
                .status(SUCCESS)
                .build());

        assertNotNull(job);
        assertEquals(1L, job.getId());
        assertEquals("updated", job.getDescription());
        assertEquals(SUCCESS, job.getDescription());
    }
}