package com.borelanjo.jobservice.presentation.dto.job;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JobCreateRequestTo {

    private String type;

    private String description;
}
