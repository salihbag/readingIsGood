package com.caseStudy.readingIsGood.api.controllers;

import com.caseStudy.readingIsGood.common.utilities.results.DataResult;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.core.requests.create.CreateOrderRequest;
import com.caseStudy.readingIsGood.core.responses.GetAllOrdersResponse;
import com.caseStudy.readingIsGood.core.services.abstracts.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/{startDate}/{endDate}/orders")
    public ResponseEntity<DataResult<List<GetAllOrdersResponse>>> getAllOrdersByDate(@PathVariable(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime fromDate,
                                                                               @PathVariable(value = "endDate")@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime toDate){
        return ResponseEntity.ok(this.orderService.getAllOrdersBetweenDates(fromDate, toDate));


    }

}
