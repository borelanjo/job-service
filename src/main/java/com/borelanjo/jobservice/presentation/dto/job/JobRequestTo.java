package com.borelanjo.jobservice.presentation.dto.job;

import com.borelanjo.jobservice.domain.model.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JobRequestTo {

    private String type;

    private String description;

    private Status status;
}
