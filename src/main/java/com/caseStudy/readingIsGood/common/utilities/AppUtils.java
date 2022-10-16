package com.caseStudy.readingIsGood.common.utilities;

import java.time.LocalDateTime;
import java.util.Base64;

public class AppUtils {

    public static String decode(String encodedString){
        return new String(Base64.getDecoder().decode(encodedString.getBytes()));
    }

    public static String encode(String plainString){
        return Base64.getEncoder().encodeToString(plainString.getBytes());
    }

    public static LocalDateTime now(){
        LocalDateTime now = LocalDateTime.now();
        return now;
    }
}
