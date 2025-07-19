package com.pragma.bootcamp.infraestructure.input.res;

import com.pragma.bootcamp.application.dto.request.BootcampRequest;
import com.pragma.bootcamp.application.dto.request.PageRequest;
import com.pragma.bootcamp.application.dto.response.BootcampResponse;
import com.pragma.bootcamp.application.dto.response.PageResponse;
import com.pragma.bootcamp.application.handler.IBootcampHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BootcampRestControllerTest {
    @Mock
    private IBootcampHandler bootcampHandler;

    @InjectMocks
    private BootcampRestController bootcampRestController;

    private WebTestClient webTestClient;

    private final BootcampRequest request = new BootcampRequest();
    private final BootcampResponse response = new BootcampResponse();

    @BeforeEach
    void setup() {
        webTestClient = WebTestClient.bindToController(bootcampRestController).build();
    }

    @Test
    void createBootcamp_ShouldReturnOk() {
        when(bootcampHandler.createBootcamp(request)).thenReturn(Mono.just(response));

        webTestClient.post()
                .uri("/bootcamp/")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BootcampResponse.class)
                .isEqualTo(response);
    }

    @Test
    void getBootcampById_ShouldReturnOk() {
        when(bootcampHandler.getBootcampById(1L)).thenReturn(Mono.just(response));

        webTestClient.get()
                .uri("/bootcamp/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(BootcampResponse.class)
                .isEqualTo(response);
    }
    @Test
    void getBootcamps_ShouldReturnOk() {
        PageResponse<BootcampResponse> pageResponse = new PageResponse<>(
                List.of(new BootcampResponse()), 1L, 0, 10,1
        );

        when(bootcampHandler.getBootcamps(any(PageRequest.class)))
                .thenReturn(Mono.just(pageResponse));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/bootcamp/")
                        .queryParam("page", 0)
                        .queryParam("size", 10)
                        .queryParam("order", "ASC")
                        .queryParam("sortBy", "NAME")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.totalElements").isEqualTo(1)
                .jsonPath("$.content.length()").isEqualTo(1);
    }
}