package com.caseStudy.readingIsGood.core.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookStockRequest {

    private int id;

    @Min(value = 0, message = "The stock must be a positive value")
    private int stock;
}
