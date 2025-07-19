package com.pragma.bootcamp.domain.usecase;

import com.pragma.bootcamp.domain.api.IBootcampServicePort;
import com.pragma.bootcamp.domain.exception.CapabilityNotFoundException;
import com.pragma.bootcamp.domain.exception.InvalidBootcampException;
import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.domain.model.web.Capability;
import com.pragma.bootcamp.domain.spi.IBootcampPersistencePort;
import com.pragma.bootcamp.domain.spi.web.ICapabilityClientPort;
import com.pragma.bootcamp.domain.util.ExceptionConstans;
import com.pragma.bootcamp.domain.util.ValueConstants;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BootcampUseCase implements IBootcampServicePort {

    private final IBootcampPersistencePort bootcampPersistencePort;
    private final ICapabilityClientPort capabilityClientPort;

    public BootcampUseCase(IBootcampPersistencePort bootcampPersistencePort, ICapabilityClientPort capabilityClientPort) {
        this.bootcampPersistencePort = bootcampPersistencePort;
        this.capabilityClientPort = capabilityClientPort;
    }

    @Override
    public Mono<Bootcamp> createBootcamp(Bootcamp cap) {
        return validateData(cap)
                .flatMap(this::validateCapabilities)
                .flatMap(bootcampPersistencePort::save);
    }

    @Override
    public Mono<Bootcamp> getBootcampById(Long id) {
        return bootcampPersistencePort.getBootcampById(id);
    }


    private Mono<Bootcamp> validateData(Bootcamp bootcamp) {
        List<Capability> capabilities = bootcamp.getCapabilities();
        List<String> errors = new ArrayList<>();

        if (bootcamp.getName() == null || bootcamp.getName().isBlank()) {
            errors.add(ExceptionConstans.BOOTCAMP_NAME_REQUIRED);
        }
       
        if (bootcamp.getDescription() == null || bootcamp.getDescription().isBlank()) {
            errors.add(ExceptionConstans.BOOTCAMP_DESCRIPTION_REQUIRED);
        }
        if (bootcamp.getDate() == null || bootcamp.getDate().toString().isBlank()) {
            errors.add(ExceptionConstans.BOOTCAMP_DATE_REQUIRED);
        }
        if (bootcamp.getDuration() == null || bootcamp.getDuration().isNegative() || bootcamp.getDuration().isZero()){
            errors.add(ExceptionConstans.BOOTCAMP_DURATION_REQUIRED);
        }
        if (capabilities.size() < ValueConstants.MIN_COUNT_CAPABILITY || capabilities.size() > ValueConstants.MAX_COUNT_CAPABILITY) {
            errors.add(ExceptionConstans.CAPABILITY_NOT_COUNT_MIN_MAX);
        }

        long unique = capabilities.stream().map(Capability::getId).filter(Objects::nonNull).distinct().count();
        if (unique != capabilities.size()) {
            errors.add(ExceptionConstans.CAPABILITY_DUPLICATE);
        }

        if (!errors.isEmpty()) {
            return Mono.error(new InvalidBootcampException(String.join("; ", errors)));
        }
        return  Mono.just(bootcamp) ;
    }
    private Mono<Bootcamp> validateCapabilities (Bootcamp bootcamp) {

        return Flux.fromIterable(bootcamp.getCapabilities())
                .flatMap(capability ->
                        capabilityClientPort.getCapabilityById(capability.getId())
                                .switchIfEmpty(Mono.error(new CapabilityNotFoundException(ExceptionConstans.CAPABILITY_NOT_FOUND + capability.getId())))
                )
                .then(Mono.just(bootcamp));


    }

}
