package com.caseStudy.readingIsGood.core.services.abstracts;

import com.caseStudy.readingIsGood.common.utilities.results.DataResult;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.core.requests.create.CreateOrderRequest;
import com.caseStudy.readingIsGood.core.responses.GetAllOrdersResponse;
import com.caseStudy.readingIsGood.domain.entities.Order;

import java.util.List;

public interface OrderService {

    Result add(CreateOrderRequest createOrderRequest);
    List<Order> getOrdersByCustomerId(int customerId);
    DataResult<List<GetAllOrdersResponse>> getAllOrders(int id);
}
