package com.caseStudy.readingIsGood.api.controllers;

import com.caseStudy.readingIsGood.common.utilities.results.DataResult;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.core.requests.create.CreateBookRequest;
import com.caseStudy.readingIsGood.core.requests.create.CreateCustomerRequest;
import com.caseStudy.readingIsGood.core.requests.update.UpdateBookStockRequest;
import com.caseStudy.readingIsGood.core.responses.GetAllBooksResponse;
import com.caseStudy.readingIsGood.core.services.abstracts.BookService;
import com.caseStudy.readingIsGood.core.services.abstracts.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Validated CreateCustomerRequest createCustomerRequest){
        return ResponseEntity.ok(this.customerService.add(createCustomerRequest));
    }


}