package com.pragma.bootcamp.application.dto.request;

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
    private LocalDateTime date;
    private Duration duration;
    private List<CapabilityRequest> capabilities;

}
