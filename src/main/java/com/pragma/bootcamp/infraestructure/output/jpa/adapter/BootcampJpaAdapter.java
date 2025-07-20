package com.pragma.bootcamp.infraestructure.output.jpa.adapter;

import com.pragma.bootcamp.domain.enumdata.SortBy;
import com.pragma.bootcamp.domain.enumdata.SortOrder;
import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.model.BootcampWithCount;
import com.pragma.bootcamp.domain.model.web.Capability;
import com.pragma.bootcamp.domain.pagination.PageModel;
import com.pragma.bootcamp.domain.pagination.PageRequestModel;
import com.pragma.bootcamp.domain.spi.IBootcampPersistencePort;
import com.pragma.bootcamp.domain.spi.web.ICapabilityClientPort;
import com.pragma.bootcamp.infraestructure.output.jpa.entity.BootcampCapabilityEntity;
import com.pragma.bootcamp.infraestructure.output.jpa.entity.BootcampEntity;
import com.pragma.bootcamp.infraestructure.output.jpa.mapper.IBootcampEntityMapper;
import com.pragma.bootcamp.infraestructure.output.jpa.repository.IBootcampCapabilityRepository;
import com.pragma.bootcamp.infraestructure.output.jpa.repository.IBootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;


@RequiredArgsConstructor
public class BootcampJpaAdapter implements IBootcampPersistencePort {

    private final IBootcampRepository bootcampRepository;
    private final IBootcampCapabilityRepository bootcampCapabilityRepository;

    private final IBootcampEntityMapper bootcampEntityMapper;
    private final ICapabilityClientPort capabilityClientPort;
    private final TransactionalOperator transactionalOperator;
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

    @Override
    public Mono<PageModel<Bootcamp>> getBootcamps(PageRequestModel pageRequest) {
        int    page      = pageRequest.getPage();
        int    size      = pageRequest.getSize();
        long   offset    = (long) page * size;

        Mono<Long> total =bootcampRepository.count();


        Flux<BootcampWithCount> pageFlux = switch (pageRequest.getSortBy()) {
            case SortBy.NAME -> pageRequest.getOrder() == SortOrder.ASC
                    ? bootcampRepository.findPageOrderByNameAsc(size, offset)
                    : bootcampRepository.findPageOrderByNameDesc(size, offset);
            case SortBy.CAPABILITY_COUNT -> pageRequest.getOrder() == SortOrder.ASC
                    ? bootcampRepository.findPageOrderByCountAsc(size, offset)
                    : bootcampRepository.findPageOrderByCountDesc(size, offset);
        };


        Mono<List<Bootcamp>> content = pageFlux
                .flatMap(proj -> {
                    BootcampEntity ent = new BootcampEntity();

                    ent.setId(proj.getId());
                    ent.setName(proj.getName());
                    ent.setDescription(proj.getDescription());
                    ent.setDate(proj.getDate());
                    long seconds = proj.getDuration() != null && proj.getDuration() > 0
                            ? proj.getDuration()
                            : 0L;
                    ent.setDuration(Duration.ofSeconds(seconds));

                    Bootcamp cap = bootcampEntityMapper.toModel(ent);

                    return bootcampCapabilityRepository.findAllByBootcampId(proj.getId())
                            .map(BootcampCapabilityEntity::getCapabilityId)
                            .collectList()
                            .flatMapMany(ids ->
                                    Flux.fromIterable(ids)
                                            .flatMap(capabilityClientPort::getCapabilityById)
                            )
                            .collectList()
                            .map(techs -> {
                                cap.setCapabilities(techs);
                                return cap;
                            });
                })
                .collectList();

        return Mono.zip(total, content)
                .map(tp -> {
                    long tot = tp.getT1();
                    List<Bootcamp> caps = tp.getT2();

                    return new PageModel<>(caps, tot, page, size);
                });
    }
    @Override
    public Mono<Void> deleteBootcamp(Long bootcampId) {

        return bootcampCapabilityRepository.findAllByBootcampId(bootcampId)
                .map(BootcampCapabilityEntity::getCapabilityId)
                .collectList()
                .flatMap(capIds ->

                        Flux.fromIterable(capIds)
                                .flatMap(capId ->
                                        bootcampCapabilityRepository.findAllByCapabilityId(capId)
                                                .count()
                                                .filter(count -> count == 1)
                                                .map(ids -> capId)
                                )
                                .collectList()
                                .flatMap(capIdsToDelete ->

                                        transactionalOperator.execute(status ->
                                                        bootcampRepository.deleteById(bootcampId)
                                                )
                                                .then(

                                                        Flux.fromIterable(capIdsToDelete)
                                                                .flatMap(capabilityClientPort::deleteCapabilityById)
                                                                .then()
                                                )
                                )
                );
    }




}
