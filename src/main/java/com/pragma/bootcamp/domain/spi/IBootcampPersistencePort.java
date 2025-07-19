package com.pragma.bootcamp.domain.spi;

import com.pragma.bootcamp.domain.model.Bootcamp;
import reactor.core.publisher.Mono;

public interface IBootcampPersistencePort {

    Mono<Bootcamp> save(Bootcamp bootcamp);
    Mono<Bootcamp> getBootcampById(Long id);
}
