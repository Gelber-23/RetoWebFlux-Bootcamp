package com.pragma.bootcamp.domain.spi.web;

import com.pragma.bootcamp.domain.model.web.Capability;
import reactor.core.publisher.Mono;

public interface ICapabilityClientPort {

    Mono<Capability> getCapabilityById(Long id);

}
