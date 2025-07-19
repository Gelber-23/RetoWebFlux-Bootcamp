package com.pragma.bootcamp.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BootcampResponse {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private Duration duration;
    private List<CapabilityResponse> capabilities;
}
