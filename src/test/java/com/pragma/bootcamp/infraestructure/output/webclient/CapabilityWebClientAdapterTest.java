package com.pragma.bootcamp.infraestructure.output.webclient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CapabilityWebClientAdapterTest {
    @Mock
    private ExchangeFunction exchangeFunction;

    private CapabilityWebClientAdapter adapter;

    @BeforeEach
    void setUp() {
        WebClient.Builder builder = WebClient.builder().exchangeFunction(exchangeFunction);
        adapter = new CapabilityWebClientAdapter(builder, "http://localhost");
    }

    @Test
    void getCapabilityById_ShouldReturnCapability() {
        ClientResponse response = ClientResponse.create(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body("""
                        {"id":1,"name":"name","description":"desc"}
                      """)
                .build();

        when(exchangeFunction.exchange(any())).thenReturn(Mono.just(response));

        StepVerifier.create(adapter.getCapabilityById(1L))
                .expectNextMatches(c -> c.getId().equals(1L) && c.getName().equals("name"))
                .verifyComplete();
    }
}