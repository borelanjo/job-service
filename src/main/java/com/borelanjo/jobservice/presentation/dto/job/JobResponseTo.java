package com.borelanjo.jobservice.presentation.dto.job;

import com.borelanjo.jobservice.domain.model.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class JobResponseTo {

    private Long id;

    private UUID code;

    private String type;

    private String description;

    private Status status;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;
}
