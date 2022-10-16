package com.caseStudy.readingIsGood.core.services.abstracts;

import com.caseStudy.readingIsGood.common.utilities.results.DataResult;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.common.utilities.results.SuccessDataResult;
import com.caseStudy.readingIsGood.core.requests.create.CreateCustomerRequest;
import com.caseStudy.readingIsGood.core.responses.GetAllCustomersResponse;
import com.caseStudy.readingIsGood.core.responses.GetAllOrdersResponse;

import java.util.List;

public interface CustomerService {

    Result add(CreateCustomerRequest createCustomerRequest);
    SuccessDataResult<GetAllCustomersResponse> getCustomerById(int id);
    DataResult<List<GetAllOrdersResponse>> getAllOrders(int customerId);
}
