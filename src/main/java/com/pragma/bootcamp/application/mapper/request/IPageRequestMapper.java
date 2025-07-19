package com.pragma.bootcamp.application.mapper.request;


import com.pragma.bootcamp.application.dto.request.PageRequest;
import com.pragma.bootcamp.domain.pagination.PageRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPageRequestMapper {
    PageRequestModel toModel (PageRequest pageRequest);
    PageRequest toRequest (PageRequestModel pageRequestModel);
}
