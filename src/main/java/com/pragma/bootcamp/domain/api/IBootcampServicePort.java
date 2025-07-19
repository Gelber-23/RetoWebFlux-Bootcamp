package com.pragma.bootcamp.domain.api;

import com.pragma.bootcamp.domain.model.Bootcamp;
import reactor.core.publisher.Mono;

public interface IBootcampServicePort {

    Mono<Bootcamp> createBootcamp(Bootcamp cap);
    Mono<Bootcamp> getBootcampById(Long id);
}
