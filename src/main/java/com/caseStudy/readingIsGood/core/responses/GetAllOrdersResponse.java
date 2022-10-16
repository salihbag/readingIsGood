package com.caseStudy.readingIsGood.core.responses;

import com.caseStudy.readingIsGood.common.enums.OrderStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrdersResponse {

    private int id;

    private LocalDateTime createdDate;

    private OrderStates state;

    private int customerId;

    private double totalPrice;

    private int totalBookCount;

}
