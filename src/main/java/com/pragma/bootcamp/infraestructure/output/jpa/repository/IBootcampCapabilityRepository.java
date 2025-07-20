package com.pragma.bootcamp.infraestructure.output.jpa.repository;

import com.pragma.bootcamp.infraestructure.output.jpa.entity.BootcampCapabilityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBootcampCapabilityRepository extends ReactiveCrudRepository<BootcampCapabilityEntity, Long> {
    Flux<BootcampCapabilityEntity> findAllByBootcampId(Long bootcampId);
    Mono<Void> deleteAllByBootcampId(Long bootcampId);
    Flux<BootcampCapabilityEntity> findAllByCapabilityId(Long capabilityId);
}