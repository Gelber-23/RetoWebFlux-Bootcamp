package com.pragma.bootcamp.application.handler.impl;


import com.pragma.bootcamp.application.dto.request.BootcampRequest;
import com.pragma.bootcamp.application.dto.request.PageRequest;
import com.pragma.bootcamp.application.dto.response.BootcampResponse;
import com.pragma.bootcamp.application.mapper.request.IBootcampRequestMapper;
import com.pragma.bootcamp.application.mapper.request.IPageRequestMapper;
import com.pragma.bootcamp.application.mapper.response.IBootcampResponseMapper;
import com.pragma.bootcamp.domain.api.IBootcampServicePort;
import com.pragma.bootcamp.domain.enumdata.SortBy;
import com.pragma.bootcamp.domain.enumdata.SortOrder;
import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.pagination.PageModel;
import com.pragma.bootcamp.domain.pagination.PageRequestModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BootcampHandlerTest {

    @Mock
    private IBootcampServicePort bootcampServicePort;
    @Mock
    private IBootcampRequestMapper bootcampRequestMapper;
    @Mock
    private IBootcampResponseMapper bootcampResponseMapper;
    @Mock
    private IPageRequestMapper pageRequestMapper;
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

    @Test
    void getBootcamps_ShouldReturnPageResponse() {
        PageRequest pageRequest = new PageRequest(0, 10, SortOrder.ASC, SortBy.NAME);
        PageRequestModel pageRequestModel = new PageRequestModel(0, 10, SortOrder.ASC,SortBy.NAME);
        Bootcamp bootcamp = new Bootcamp();
        BootcampResponse response = new BootcampResponse();
        PageModel<Bootcamp> pageModel = new PageModel<>(List.of(bootcamp), 1L, 0, 10);

        when(pageRequestMapper.toModel(pageRequest)).thenReturn(pageRequestModel);
        when(bootcampServicePort.getBootcamps(pageRequestModel)).thenReturn(Mono.just(pageModel));
        when(bootcampResponseMapper.toResponse(bootcamp)).thenReturn(response);

        StepVerifier.create(bootcampHandler.getBootcamps(pageRequest))
                .expectNextMatches(result ->
                        result.getContent().size() == 1 &&
                                result.getTotalElements() == 1L &&
                                result.getTotalPages() == 1
                )
                .verifyComplete();
    }
}