package com.pragma.bootcamp.application.handler.impl;

import com.pragma.bootcamp.application.dto.request.BootcampRequest;
import com.pragma.bootcamp.application.dto.response.BootcampResponse;
import com.pragma.bootcamp.application.handler.IBootcampHandler;
import com.pragma.bootcamp.application.mapper.request.IBootcampRequestMapper;
import com.pragma.bootcamp.application.mapper.response.IBootcampResponseMapper;
import com.pragma.bootcamp.domain.api.IBootcampServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class BootcampHandler implements IBootcampHandler {

    private final IBootcampServicePort bootcampServicePort;
    private final IBootcampRequestMapper bootcampRequestMapper;
    private final IBootcampResponseMapper bootcampResponseMapper;


    @Override
    public Mono<BootcampResponse> createBootcamp(BootcampRequest cap) {

        return Mono.just(cap)
                .map(bootcampRequestMapper::toModel)
                .flatMap(bootcampServicePort::createBootcamp)
                .map(bootcampResponseMapper::toResponse);

    }

    @Override
    public Mono<BootcampResponse> getBootcampById(Long id) {
        return bootcampServicePort.getBootcampById(id)
                .map(bootcampResponseMapper::toResponse);
    }
}
