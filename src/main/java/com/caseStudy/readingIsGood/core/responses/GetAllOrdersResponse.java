package com.caseStudy.readingIsGood.core.responses;

import com.caseStudy.readingIsGood.common.enums.OrderStates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllOrdersResponse {

    private int id;

    private LocalDateTime createdDate;

    private OrderStates state;

    private int customerId;

    private double totalPrice;

    private int totalBookCount;

    public static GetAllOrdersResponse fromAllOrdersResponse(GetAllOrdersResponse order) {

        return GetAllOrdersResponse.builder()
                .createdDate(order.getCreatedDate())
                .state(order.getState())
                .customerId(order.getCustomerId())
                .totalPrice(order.getTotalPrice())
                .totalBookCount(order.getTotalBookCount())
                .build();
    }

}
