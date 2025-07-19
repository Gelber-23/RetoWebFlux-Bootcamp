package com.pragma.bootcamp.infraestructure.configuration;

import com.pragma.bootcamp.domain.api.IBootcampServicePort;
import com.pragma.bootcamp.domain.spi.IBootcampPersistencePort;
import com.pragma.bootcamp.domain.usecase.BootcampUseCase;
import com.pragma.bootcamp.infraestructure.output.jpa.adapter.BootcampJpaAdapter;
import com.pragma.bootcamp.infraestructure.output.jpa.mapper.IBootcampEntityMapper;
import com.pragma.bootcamp.infraestructure.output.jpa.repository.IBootcampCapabilityRepository;
import com.pragma.bootcamp.infraestructure.output.jpa.repository.IBootcampRepository;
import com.pragma.bootcamp.infraestructure.output.webclient.CapabilityWebClientAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;
    private final IBootcampCapabilityRepository bootcampCapabilityRepository;
    private final CapabilityWebClientAdapter capabilityWebClientAdapter;

    @Bean
    public IBootcampPersistencePort bootcampPersistencePort(){
        return new BootcampJpaAdapter(bootcampRepository, bootcampCapabilityRepository, bootcampEntityMapper,capabilityWebClientAdapter);
    }

    @Bean
    public IBootcampServicePort bootcampServicePort(){
        return new BootcampUseCase(bootcampPersistencePort(),capabilityWebClientAdapter);
    }
}
