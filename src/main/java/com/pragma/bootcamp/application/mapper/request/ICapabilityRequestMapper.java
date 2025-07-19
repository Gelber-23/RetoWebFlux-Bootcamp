package com.pragma.bootcamp.application.mapper.request;

import com.pragma.bootcamp.application.dto.request.CapabilityRequest;
import com.pragma.bootcamp.domain.model.web.Capability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICapabilityRequestMapper {
    Capability toModel (CapabilityRequest capabilityRequest);
}
