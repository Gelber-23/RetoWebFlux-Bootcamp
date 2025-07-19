package com.pragma.bootcamp.infraestructure.output.jpa.mapper;

import com.pragma.bootcamp.domain.model.Bootcamp;
import com.pragma.bootcamp.infraestructure.output.jpa.entity.BootcampEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBootcampEntityMapper {
    Bootcamp toModel (BootcampEntity bootcampEntity);
    BootcampEntity toEntity ( Bootcamp bootcamp);

}
