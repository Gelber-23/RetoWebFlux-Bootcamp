package com.pragma.bootcamp.domain.util;

public class ExceptionConstans {


    public static final String FIRST_PART_MESSAGE_EXCEPTION = "Message" ;
    public static final String STATUS_MESSAGE_EXCEPTION = "Error";
    public static final String CAPABILITY_NOT_COUNT_MIN_MAX = "Capabilities count must be " + ValueConstants.MIN_COUNT_CAPABILITY + " - " + ValueConstants.MAX_COUNT_CAPABILITY ;
    public static final String CAPABILITY_DUPLICATE = "Duplicate Capability IDs" ;
    public static final String CAPABILITY_NOT_FOUND = "Capability not found, id: " ;

    public static final String BOOTCAMP_NAME_REQUIRED= "Name must not be empty";
    public static final String BOOTCAMP_DESCRIPTION_REQUIRED = "Description must not be empty";
    public static final String BOOTCAMP_DATE_REQUIRED= "Date must not be empty";
    public static final String BOOTCAMP_DURATION_REQUIRED= "Duration must not be empty";


    public static final String VALUE_NOT_IN_ENUM ="%s Invalid parameter '%s': '%s'. Allowed values: [%s]";

}
