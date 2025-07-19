package com.pragma.bootcamp.infraestructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Duration;
import java.time.LocalDateTime;

@Table("bootcamps")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BootcampEntity {
    @Id
    private Long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private Duration duration;
}
