package com.pragma.bootcamp.domain.model.web;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class BootcampReport {


        private Long bootcampId;
        private String name;
        private String description;
        private LocalDateTime date;
        private Duration duration;
        private int capabilityCount;
        private long technologyCount;
        private int personCount;
    private List<Capability> capabilities;

    public Long getBootcampId() {
        return bootcampId;
    }

    public void setBootcampId(Long bootcampId) {
        this.bootcampId = bootcampId;
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

    public int getCapabilityCount() {
        return capabilityCount;
    }

    public void setCapabilityCount(int capabilityCount) {
        this.capabilityCount = capabilityCount;
    }

    public long getTechnologyCount() {
        return technologyCount;
    }

    public void setTechnologyCount(long technologyCount) {
        this.technologyCount = technologyCount;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public List<Capability> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<Capability> capabilities) {
        this.capabilities = capabilities;
    }

    public BootcampReport() {
    }

    public BootcampReport(Long bootcampId, String name, String description, LocalDateTime date, Duration duration, int capabilityCount, long technologyCount, int personCount, List<Capability> capabilities) {
        this.bootcampId = bootcampId;
        this.name = name;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.capabilityCount = capabilityCount;
        this.technologyCount = technologyCount;
        this.personCount = personCount;
        this.capabilities = capabilities;
    }
}
