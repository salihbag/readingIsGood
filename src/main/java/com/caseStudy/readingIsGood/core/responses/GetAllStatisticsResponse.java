package com.caseStudy.readingIsGood.core.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllStatisticsResponse {

    private String month;

    private int totalOrderCount;

    private int totalBookCount;

    private Double totalPurchasedAmount;
}
