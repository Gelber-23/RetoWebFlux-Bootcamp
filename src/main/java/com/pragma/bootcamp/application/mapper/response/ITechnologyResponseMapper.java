package com.pragma.bootcamp.application.mapper.response;

import com.pragma.bootcamp.application.dto.response.TechnologyResponse;
import com.pragma.bootcamp.domain.model.web.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITechnologyResponseMapper {
    TechnologyResponse toResponse (Technology technology);
}
