package com.caseStudy.readingIsGood.api.controllers;

import com.caseStudy.readingIsGood.common.utilities.results.DataResult;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.core.requests.create.CreateOrderRequest;
import com.caseStudy.readingIsGood.core.responses.GetAllOrdersResponse;
import com.caseStudy.readingIsGood.core.services.abstracts.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Validated CreateOrderRequest createOrderRequest){
        return ResponseEntity.ok(this.orderService.add(createOrderRequest));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<DataResult<List<GetAllOrdersResponse>>> getAllOrders(@PathVariable int id){
        return ResponseEntity.ok(this.orderService.getAllOrders(id));
    }

}
