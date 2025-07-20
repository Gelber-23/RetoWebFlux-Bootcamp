package com.pragma.bootcamp.application.handler;

import com.pragma.bootcamp.application.dto.request.BootcampRequest;
import com.pragma.bootcamp.application.dto.request.PageRequest;
import com.pragma.bootcamp.application.dto.response.BootcampResponse;
import com.pragma.bootcamp.application.dto.response.PageResponse;
import reactor.core.publisher.Mono;

public interface IBootcampHandler {
    Mono<BootcampResponse> createBootcamp(BootcampRequest cap);
    Mono<BootcampResponse> getBootcampById(Long id);
    Mono<PageResponse<BootcampResponse>> getBootcamps(PageRequest pageRequest);
    Mono<Void> deleteBootcamp(Long id);
}
