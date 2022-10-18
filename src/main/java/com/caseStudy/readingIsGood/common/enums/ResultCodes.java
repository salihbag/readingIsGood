package com.caseStudy.readingIsGood.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCodes {

    //Code message equivalents can be kept in the database and get from the cache so that they can be changed dynamically.

    BOOK_ADDED("BS100"),
    BOOK_STOCK_UPDATED("BS101"),
    BOOK_NOT_EXISTS("BE100"),
    BOOK_EXISTS("BE101"),
    BOOK_STOCK_ERROR("BE102"),

    CUSTOMER_ADDED("CS100"),
    EMAIL_EXISTS("CE100"),
    CUSTOMER_NOT_EXISTS("CE101"),

    ORDER_CREATED("OS100"),
    ORDER_NOT_EXISTS("OE100"),

    USER_NOT_FOUND("AE100"),
    AUTHORIZATION_INVALID("AE101");

    private final String code;


}
