package com.pragma.bootcamp.infraestructure.output.jpa.repository;

import com.pragma.bootcamp.infraestructure.output.jpa.entity.BootcampEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IBootcampRepository  extends ReactiveCrudRepository<BootcampEntity,Long> {
}
