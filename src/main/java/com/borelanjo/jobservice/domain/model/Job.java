package com.borelanjo.jobservice.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Job {

    private Long id;
    private Status status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String type;
    private String description;
}
