package com.borelanjo.jobservice.infrastructure.persistence.hibernate.repository;

import com.borelanjo.jobservice.domain.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
