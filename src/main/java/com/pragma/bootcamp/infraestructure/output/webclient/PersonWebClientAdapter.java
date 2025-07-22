package com.pragma.bootcamp.infraestructure.output.webclient;

import com.pragma.bootcamp.domain.model.web.Capability;
import com.pragma.bootcamp.domain.spi.web.IPersonClientPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Component
public class PersonWebClientAdapter implements IPersonClientPort {


    private final WebClient webClient;

    public PersonWebClientAdapter(WebClient.Builder builder, @Value("${client.person}") String capabilityUrl) {


        this.webClient = builder.baseUrl(capabilityUrl).build();
    }

    @Override
    public Mono<Long> countByBootcampId(Long bootcampId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/bootcamp/{id}/count")
                        .build(bootcampId))
                .retrieve()
                .bodyToMono(Long.class);
    }
}
