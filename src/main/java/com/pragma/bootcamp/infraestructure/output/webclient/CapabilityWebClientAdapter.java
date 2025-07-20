package com.pragma.bootcamp.infraestructure.output.webclient;

import com.pragma.bootcamp.domain.model.web.Capability;
import com.pragma.bootcamp.domain.spi.web.ICapabilityClientPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CapabilityWebClientAdapter  implements ICapabilityClientPort {

    private final WebClient webClient;

    public CapabilityWebClientAdapter(WebClient.Builder builder, @Value("${client.capability}") String capabilityUrl) {


        this.webClient = builder.baseUrl(capabilityUrl).build();
    }


    @Override
    public Mono<Capability> getCapabilityById(Long id) {
        return
                webClient.get()
                        .uri("/" + id)
                        .retrieve()
                        .bodyToMono(Capability.class);

    }

    @Override
    public Mono<Void> deleteCapabilityById(Long id) {
        return webClient
                .delete()
                .uri("/" + id)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return Mono.empty();
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                });

    }
}
