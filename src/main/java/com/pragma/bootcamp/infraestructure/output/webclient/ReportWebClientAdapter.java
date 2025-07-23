package com.pragma.bootcamp.infraestructure.output.webclient;

import com.pragma.bootcamp.domain.model.web.BootcampReport;
import com.pragma.bootcamp.domain.spi.web.IReportClientPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ReportWebClientAdapter implements IReportClientPort {

    private final WebClient webClient;

    public ReportWebClientAdapter(WebClient.Builder builder, @Value("${client.report}") String reportUrl) {


        this.webClient = builder.baseUrl(reportUrl).build();
    }

    @Override
    public Mono<Void> createDatBootcampReport(BootcampReport bootcampReport) {
        return  webClient.post()
                .uri("/bootcamp")
                .bodyValue(bootcampReport)
                .retrieve().bodyToMono(Void.class)
                ;
    }
}
