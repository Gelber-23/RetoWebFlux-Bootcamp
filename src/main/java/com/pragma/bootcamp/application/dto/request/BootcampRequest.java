package com.pragma.bootcamp.application.dto.request;

import com.pragma.bootcamp.domain.util.OpenApiConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BootcampRequest {

    private String name;
    private String description;
    @Schema(
            description = OpenApiConstants.BOOTCAMP_DATE_DESCRIPTION,
            example =  OpenApiConstants.BOOTCAMP_DATE_EXAMPLE
    )
    private LocalDateTime date;

    @Schema(
            description = OpenApiConstants.BOOTCAMP_DURATION_DESCRIPTION,
            example = OpenApiConstants.BOOTCAMP_DURATION_EXAMPLE
    )
    private Duration duration;
    private List<CapabilityRequest> capabilities;

}
