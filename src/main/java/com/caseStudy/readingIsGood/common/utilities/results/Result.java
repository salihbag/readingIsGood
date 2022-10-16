package com.caseStudy.readingIsGood.common.utilities.results;

import lombok.Getter;

@Getter
public class Result {
    boolean success;
    String message;

    public Result (boolean success){
        this.success = success;
    }
    public Result (boolean success, String message){
        this(success);
        this.message = message;
    }

}
