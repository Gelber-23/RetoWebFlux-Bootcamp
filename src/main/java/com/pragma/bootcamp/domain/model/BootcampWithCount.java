package com.pragma.bootcamp.domain.model;

import java.time.LocalDateTime;

public interface BootcampWithCount {
    Long             getId();
    String           getName();
    String           getDescription();
    LocalDateTime getDate();
    Long             getDuration();
    Long             getCapabilityCount();
}