package com.pragma.bootcamp.application.mapper.response;

import com.pragma.bootcamp.application.dto.response.BootcampResponse;
import com.pragma.bootcamp.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBootcampResponseMapper {

    BootcampResponse toResponse (Bootcamp bootcamp);
}
