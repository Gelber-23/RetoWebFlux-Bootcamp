package com.pragma.bootcamp.application.mapper.request;

import com.pragma.bootcamp.application.dto.request.BootcampRequest;
import com.pragma.bootcamp.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBootcampRequestMapper {
    Bootcamp toModel (BootcampRequest bootcampRequest);
}
