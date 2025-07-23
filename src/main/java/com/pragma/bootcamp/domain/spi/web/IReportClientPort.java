package com.pragma.bootcamp.domain.spi.web;

import com.pragma.bootcamp.domain.model.web.BootcampReport;
import reactor.core.publisher.Mono;

public interface IReportClientPort {
    Mono<Void>  createDatBootcampReport (BootcampReport bootcampReport);
}
