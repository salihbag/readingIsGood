package com.caseStudy.readingIsGood.core.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -1381401168942676208L;

    private final String jwtToken;

}