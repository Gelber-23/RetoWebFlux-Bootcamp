package com.pragma.bootcamp.domain.pagination;

import com.pragma.bootcamp.domain.enumdata.SortBy;
import com.pragma.bootcamp.domain.enumdata.SortOrder;

public class PageRequestModel {
    private final int page;
    private final int size;
    private final SortOrder order;
    private final SortBy sortBy;


    public PageRequestModel(int page, int size, SortOrder order, SortBy sortBy) {
        this.page = page;
        this.size = size;
        this.order = order;
        this.sortBy = sortBy;
    }
    public int getPage()       { return page; }
    public int getSize()       { return size; }
    public SortOrder getOrder(){ return order; }
    public SortBy getSortBy()  { return sortBy; }
}