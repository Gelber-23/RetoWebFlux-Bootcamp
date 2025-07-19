package com.pragma.bootcamp.domain.model;

import com.pragma.bootcamp.domain.model.web.Capability;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Bootcamp {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private Duration duration;
    private List<Capability> capabilities;

    public Bootcamp() {
    }

    public Bootcamp(Long id, String name, String description, LocalDateTime date, Duration duration, List<Capability> capabilities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.capabilities = capabilities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<Capability> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<Capability> capabilities) {
        this.capabilities = capabilities;
    }
}
