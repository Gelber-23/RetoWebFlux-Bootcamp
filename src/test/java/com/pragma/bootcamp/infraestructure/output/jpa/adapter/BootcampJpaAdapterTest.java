package com.pragma.bootcamp.infraestructure.output.jpa.adapter;


import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.model.web.Capability;
import com.pragma.bootcamp.domain.model.web.Technology;
import com.pragma.bootcamp.domain.spi.web.ICapabilityClientPort;
import com.pragma.bootcamp.infraestructure.output.jpa.entity.BootcampCapabilityEntity;
import com.pragma.bootcamp.infraestructure.output.jpa.entity.BootcampEntity;
import com.pragma.bootcamp.infraestructure.output.jpa.mapper.IBootcampEntityMapper;
import com.pragma.bootcamp.infraestructure.output.jpa.repository.IBootcampCapabilityRepository;
import com.pragma.bootcamp.infraestructure.output.jpa.repository.IBootcampRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BootcampJpaAdapterTest {

    @Mock
    private IBootcampRepository bootcampRepository;
    @Mock
    private IBootcampCapabilityRepository bootcampCapabilityRepository;
    @Mock
    private IBootcampEntityMapper bootcampEntityMapper;
    @Mock
    private ICapabilityClientPort capabilityClientPort;
    @InjectMocks
    private BootcampJpaAdapter bootcampJpaAdapter;

    private final Bootcamp bootcamp = new Bootcamp(1L, "Boot", "Desc", LocalDateTime.now(), Duration.ofDays(5), List.of(new Capability(1L, "name", "desc",List.of(new Technology(1L,"A")))));
    private final BootcampEntity entity = new BootcampEntity(1L, "Boot", "Desc", LocalDateTime.now(), Duration.ofDays(5));
    private final BootcampCapabilityEntity capabilityEntity = new BootcampCapabilityEntity(1L, 1L, 1L);

    @Test
    void save_ShouldReturnSavedBootcamp() {
        when(bootcampEntityMapper.toEntity(bootcamp)).thenReturn(entity);
        when(bootcampRepository.save(entity)).thenReturn(Mono.just(entity));
        when(bootcampCapabilityRepository.deleteAllByBootcampId(1L)).thenReturn(Mono.empty());
        when(bootcampCapabilityRepository.save(any())).thenReturn(Mono.just(capabilityEntity));
        when(capabilityClientPort.getCapabilityById(1L)).thenReturn(Mono.just(bootcamp.getCapabilities().get(0)));
        when(bootcampEntityMapper.toModel(entity)).thenReturn(bootcamp);

        StepVerifier.create(bootcampJpaAdapter.save(bootcamp))
                .expectNextMatches(saved -> saved.getId().equals(1L) && !saved.getCapabilities().isEmpty())
                .verifyComplete();
    }

    @Test
    void getBootcampById_ShouldReturnBootcamp() {
        when(bootcampRepository.findById(1L)).thenReturn(Mono.just(entity));
        when(bootcampCapabilityRepository.findAllByBootcampId(1L)).thenReturn(Flux.just(capabilityEntity));
        when(capabilityClientPort.getCapabilityById(1L)).thenReturn(Mono.just(bootcamp.getCapabilities().get(0)));
        when(bootcampEntityMapper.toModel(entity)).thenReturn(bootcamp);

        StepVerifier.create(bootcampJpaAdapter.getBootcampById(1L))
                .expectNextMatches(found -> found.getId().equals(1L) && !found.getCapabilities().isEmpty())
                .verifyComplete();
    }

    @Test
    void getBootcampById_ShouldReturnEmpty_WhenNotFound() {
        when(bootcampRepository.findById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(bootcampJpaAdapter.getBootcampById(1L))
                .verifyComplete();
    }
}