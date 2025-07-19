package com.pragma.bootcamp.application.handler.impl;


import com.pragma.bootcamp.application.dto.request.BootcampRequest;
import com.pragma.bootcamp.application.dto.response.BootcampResponse;
import com.pragma.bootcamp.application.mapper.request.IBootcampRequestMapper;
import com.pragma.bootcamp.application.mapper.response.IBootcampResponseMapper;
import com.pragma.bootcamp.domain.api.IBootcampServicePort;
import com.pragma.bootcamp.domain.model.Bootcamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BootcampHandlerTest {

    @Mock
    private IBootcampServicePort bootcampServicePort;
    @Mock
    private IBootcampRequestMapper bootcampRequestMapper;
    @Mock
    private IBootcampResponseMapper bootcampResponseMapper;
    @InjectMocks
    private BootcampHandler bootcampHandler;

    @Test
    void createBootcamp_ShouldReturnResponse() {
        BootcampRequest request = new BootcampRequest();
        Bootcamp model = new Bootcamp();
        Bootcamp savedModel = new Bootcamp();
        BootcampResponse response = new BootcampResponse();

        when(bootcampRequestMapper.toModel(request)).thenReturn(model);
        when(bootcampServicePort.createBootcamp(model)).thenReturn(Mono.just(savedModel));
        when(bootcampResponseMapper.toResponse(savedModel)).thenReturn(response);

        StepVerifier.create(bootcampHandler.createBootcamp(request))
                .expectNext(response)
                .verifyComplete();
    }

    @Test
    void getBootcampById_ShouldReturnResponse() {
        Bootcamp model = new Bootcamp();
        BootcampResponse response = new BootcampResponse();

        when(bootcampServicePort.getBootcampById(1L)).thenReturn(Mono.just(model));
        when(bootcampResponseMapper.toResponse(model)).thenReturn(response);

        StepVerifier.create(bootcampHandler.getBootcampById(1L))
                .expectNext(response)
                .verifyComplete();
    }
}