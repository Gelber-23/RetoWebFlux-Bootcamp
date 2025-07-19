package com.pragma.bootcamp.domain.usecase;

import com.pragma.bootcamp.domain.exception.CapabilityNotFoundException;
import com.pragma.bootcamp.domain.exception.InvalidBootcampException;
import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.model.web.Capability;
import com.pragma.bootcamp.domain.model.web.Technology;
import com.pragma.bootcamp.domain.spi.IBootcampPersistencePort;
import com.pragma.bootcamp.domain.spi.web.ICapabilityClientPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BootcampUseCaseTest {

    @Mock
    private IBootcampPersistencePort bootcampPersistencePort;
    @Mock
    private ICapabilityClientPort capabilityClientPort;
    @InjectMocks
    private BootcampUseCase bootcampUseCase;

    private Bootcamp validBootcamp;

    @BeforeEach
    void setUp() {
        Capability cap = new Capability(1L, "name", "desc",List.of(new Technology(1L,"A")));
        validBootcamp = new Bootcamp(1L, "Bootcamp", "Desc", LocalDateTime.now(), Duration.ofDays(10), List.of(cap));
    }

    @Test
    void createBootcamp_ShouldSave_WhenValid() {
        when(capabilityClientPort.getCapabilityById(1L)).thenReturn(Mono.just(new Capability(1L, "name", "desc",List.of(new Technology(1L,"A")))));
        when(bootcampPersistencePort.save(validBootcamp)).thenReturn(Mono.just(validBootcamp));

        StepVerifier.create(bootcampUseCase.createBootcamp(validBootcamp))
                .expectNext(validBootcamp)
                .verifyComplete();
    }

    @Test
    void createBootcamp_ShouldError_WhenValidationFails() {
        Bootcamp invalid = new Bootcamp(null, "", "", null, Duration.ZERO, List.of());
        StepVerifier.create(bootcampUseCase.createBootcamp(invalid))
                .expectErrorSatisfies(ex -> assertInstanceOf(InvalidBootcampException.class, ex))
                .verify();
    }

    @Test
    void createBootcamp_ShouldError_WhenCapabilitiesDuplicate() {
        Capability c = new Capability(1L, "n", "d",List.of(new Technology(1L,"A")));
        Bootcamp dupBootcamp = new Bootcamp(1L, "n", "d", LocalDateTime.now(), Duration.ofDays(1), List.of(c, c));
        StepVerifier.create(bootcampUseCase.createBootcamp(dupBootcamp))
                .expectErrorSatisfies(ex -> assertInstanceOf(InvalidBootcampException.class, ex))
                .verify();
    }

    @Test
    void createBootcamp_ShouldError_WhenCapabilityNotFound() {
        when(capabilityClientPort.getCapabilityById(1L)).thenReturn(Mono.empty());
        StepVerifier.create(bootcampUseCase.createBootcamp(validBootcamp))
                .expectErrorSatisfies(ex -> assertInstanceOf(CapabilityNotFoundException.class, ex))
                .verify();
    }

    @Test
    void getBootcampById_ShouldReturnBootcamp() {
        when(bootcampPersistencePort.getBootcampById(1L)).thenReturn(Mono.just(validBootcamp));
        StepVerifier.create(bootcampUseCase.getBootcampById(1L))
                .expectNext(validBootcamp)
                .verifyComplete();
    }
}