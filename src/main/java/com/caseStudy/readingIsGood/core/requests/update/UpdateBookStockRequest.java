package com.caseStudy.readingIsGood.core.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookStockRequest {

    private int id;

    private int stock;
}
