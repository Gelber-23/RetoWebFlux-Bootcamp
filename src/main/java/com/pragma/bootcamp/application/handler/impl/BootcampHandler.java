package com.pragma.bootcamp.application.handler.impl;

import com.pragma.bootcamp.application.dto.request.BootcampRequest;
import com.pragma.bootcamp.application.dto.request.PageRequest;
import com.pragma.bootcamp.application.dto.response.BootcampResponse;
import com.pragma.bootcamp.application.dto.response.PageResponse;
import com.pragma.bootcamp.application.handler.IBootcampHandler;
import com.pragma.bootcamp.application.mapper.request.IBootcampRequestMapper;
import com.pragma.bootcamp.application.mapper.request.IPageRequestMapper;
import com.pragma.bootcamp.application.mapper.response.IBootcampResponseMapper;
import com.pragma.bootcamp.domain.api.IBootcampServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BootcampHandler implements IBootcampHandler {

    private final IBootcampServicePort bootcampServicePort;
    private final IBootcampRequestMapper bootcampRequestMapper;
    private final IBootcampResponseMapper bootcampResponseMapper;
    private final IPageRequestMapper pageRequestMapper;

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

    @Override
    public Mono<PageResponse<BootcampResponse>> getBootcamps(PageRequest pageRequest) {
        return bootcampServicePort.getBootcamps(pageRequestMapper.toModel(pageRequest))
                .map(pageModel -> {

                    List<BootcampResponse> listCapability = pageModel.getContent().stream()
                            .map(bootcampResponseMapper::toResponse)
                            .toList();

                    return new PageResponse<>(
                            listCapability,
                            pageModel.getTotalElements(),
                            pageModel.getPageNumber(),
                            pageModel.getPageSize(),
                            pageModel.getTotalPages()
                    );
                });
    }
}
