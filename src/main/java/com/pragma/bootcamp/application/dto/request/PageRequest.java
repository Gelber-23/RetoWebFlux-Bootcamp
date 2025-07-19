package com.pragma.bootcamp.application.dto.request;

import com.pragma.bootcamp.domain.enumdata.SortBy;
import com.pragma.bootcamp.domain.enumdata.SortOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageRequest {
    private int page;
    private int size;
    private SortOrder order;
    private SortBy sortBy;
}
