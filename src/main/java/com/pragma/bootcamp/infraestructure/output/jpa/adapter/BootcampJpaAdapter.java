package com.pragma.bootcamp.infraestructure.output.jpa.adapter;

import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.model.web.Capability;
import com.pragma.bootcamp.domain.spi.IBootcampPersistencePort;
import com.pragma.bootcamp.domain.spi.web.ICapabilityClientPort;
import com.pragma.bootcamp.infraestructure.output.jpa.entity.BootcampCapabilityEntity;
import com.pragma.bootcamp.infraestructure.output.jpa.mapper.IBootcampEntityMapper;
import com.pragma.bootcamp.infraestructure.output.jpa.repository.IBootcampCapabilityRepository;
import com.pragma.bootcamp.infraestructure.output.jpa.repository.IBootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class BootcampJpaAdapter implements IBootcampPersistencePort {

    private final IBootcampRepository bootcampRepository;
    private final IBootcampCapabilityRepository bootcampCapabilityRepository;

    private final IBootcampEntityMapper bootcampEntityMapper;
    private final ICapabilityClientPort capabilityClientPort;

    @Override
    @Transactional
    public Mono<Bootcamp> save(Bootcamp bootcamp) {
        return bootcampRepository
                .save(bootcampEntityMapper.toEntity(bootcamp))
                .flatMap(savedEntity -> {
                    Long bootcampId = savedEntity.getId();
                    return bootcampCapabilityRepository
                            .deleteAllByBootcampId(bootcampId)

                            .thenMany(Flux.fromIterable(bootcamp.getCapabilities()))
                            .map(c -> new BootcampCapabilityEntity(null, bootcampId, c.getId()))
                            .flatMap(bootcampCapabilityRepository::save)

                            .thenMany(Flux.fromIterable(bootcamp.getCapabilities()).map(Capability::getId))
                            .flatMap(capabilityClientPort::getCapabilityById)
                            .collectList()

                            .map(fullCap -> {
                                Bootcamp bootcampModel = bootcampEntityMapper.toModel(savedEntity);
                                bootcampModel.setCapabilities(fullCap);
                                return bootcampModel;
                            });
                });
    }

    @Override
    public Mono<Bootcamp> getBootcampById(Long id) {
        return bootcampRepository.findById(id)
                .flatMap(entity ->
                        bootcampCapabilityRepository.findAllByBootcampId(id)
                                .map(BootcampCapabilityEntity::getCapabilityId)
                                .collectList()

                                .flatMap(capabilityIds ->
                                        Flux.fromIterable(capabilityIds)
                                                .flatMap(capabilityClientPort::getCapabilityById)
                                                .collectList()
                                                .map(fullCapabilities -> {
                                                    Bootcamp bootcamp = bootcampEntityMapper.toModel(entity);
                                                    bootcamp.setCapabilities(fullCapabilities);
                                                    return bootcamp;
                                                })
                                )
                );
    }
}
