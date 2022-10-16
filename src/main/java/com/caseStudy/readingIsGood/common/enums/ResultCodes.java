package com.caseStudy.readingIsGood.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCodes {

    //Code message equivalents can be kept in the database and get from the cache so that they can be changed dynamically.

    BOOK_ADDED("BS100"),
    BOOK_STOCK_UPDATED("BS101"),
    BOOK_NOT_EXISTS("BE102"),
    BOOK_EXISTS("BE103"),
    BOOK_STOCK_ERROR("BE104"),

    CUSTOMER_ADDED("CS100"),
    EMAIL_EXISTS("CE101"),

    ORDER_CREATED("OS100");

    private String code;


}
