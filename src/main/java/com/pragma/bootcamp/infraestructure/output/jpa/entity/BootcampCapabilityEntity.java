package com.pragma.bootcamp.infraestructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("bootcamp_capability")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BootcampCapabilityEntity {
    @Id
    private Long id;
    private Long bootcampId;
    private Long capabilityId;

}
