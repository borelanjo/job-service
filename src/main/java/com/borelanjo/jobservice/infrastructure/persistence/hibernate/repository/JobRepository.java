package com.borelanjo.jobservice.infrastructure.persistence.hibernate.repository;

import com.borelanjo.jobservice.domain.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, Long> {

    public Optional<Job> findByCode(UUID code);
}
