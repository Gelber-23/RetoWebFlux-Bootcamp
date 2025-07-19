package com.pragma.bootcamp.application.mapper.response;

import com.pragma.bootcamp.application.dto.response.CapabilityResponse;
import com.pragma.bootcamp.domain.model.web.Capability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICapabilityResponseMapper {
    CapabilityResponse toResponse (Capability capability);
}
