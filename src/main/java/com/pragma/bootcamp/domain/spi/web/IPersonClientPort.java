package com.pragma.bootcamp.domain.spi.web;

import reactor.core.publisher.Mono;

public interface IPersonClientPort {
    Mono<Long> countByBootcampId(Long bootcampId);
}
