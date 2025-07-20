package com.pragma.bootcamp.domain.util;

public class OpenApiConstants {

    public static final String VALIDATIONS_ERRORS_MESSAGE = "Validation errors";
    public static final String NO_DATA_MESSAGE = "No data found";
    public static final String TITLE_SWAGGER_BEARER_TOKEN ="Bearer";

    public static final String RESPONSE_CODE_201 = "201";
    public static final String RESPONSE_CODE_400 = "400";
    public static final String RESPONSE_CODE_200 = "200";
    public static final String RESPONSE_CODE_404 = "404";
    public static final String RESPONSE_CODE_409 = "409";

    // BOOTCAMP CONSTANTS API
    public static final String TITLE_BOOTCAMP_REST = "BOOTCAMP";
    public static final String TITLE_DESCRIPTION_BOOTCAMP_REST = "Endpoints for bootcamps";

    public static final String NEW_BOOTCAMP_TITLE = "Add a new Bootcamp";
    public static final String NEW_BOOTCAMP_CREATED_MESSAGE = "Bootcamp created";

    public static final String GET_BOOTCAMP_TITLE = "Get bootcamp by ID";
    public static final String GET_BOOTCAMP_MESSAGE = "Bootcamp found";

    public static final String GET_ALL_BOOTCAMP_TITLE = "Get all bootcamps";
    public static final String GET_ALL_BOOTCAMP_MESSAGE = "All bootcamps returned";

    public static final String GET_ALL_BOOTCAMP_ORDER_DESCRIPTION="Order of results (ASC for ascending, DESC for descending)";
    public static final String GET_ALL_BOOTCAMP_SORT_BY_DESCRIPTION="Field by which the results are sorted";
    public static final String DELETE_BOOTCAMP_TITLE = "Delete bootcamp by ID";
    public static final String DELETE_BOOTCAMP_MESSAGE = "Bootcamp deleted";
    //SCHEMA

    public static final String BOOTCAMP_DATE_DESCRIPTION="Bootcamp start date and time in ISO‑8601 format";
    public static final String BOOTCAMP_DATE_EXAMPLE="2025-07-20T14:30:00";

    public static final String BOOTCAMP_DURATION_DESCRIPTION="Total bootcamp duration using ISO‑8601 format for `java.time.Duration`";
    public static final String BOOTCAMP_DURATION_EXAMPLE="PT6H30M";
}
