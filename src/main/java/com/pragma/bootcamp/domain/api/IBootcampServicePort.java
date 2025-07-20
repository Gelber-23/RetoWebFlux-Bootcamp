package com.pragma.bootcamp.domain.api;

import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.pagination.PageModel;
import com.pragma.bootcamp.domain.pagination.PageRequestModel;
import reactor.core.publisher.Mono;

public interface IBootcampServicePort {

    Mono<Bootcamp> createBootcamp(Bootcamp cap);
    Mono<Bootcamp> getBootcampById(Long id);
    Mono<PageModel<Bootcamp>> getBootcamps(PageRequestModel pageRequest);
    Mono<Void> deleteBootcamp(Long id);
}
