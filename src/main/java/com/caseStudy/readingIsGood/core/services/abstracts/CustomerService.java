package com.caseStudy.readingIsGood.core.services.abstracts;

import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.core.requests.create.CreateCustomerRequest;

public interface CustomerService {

    Result add(CreateCustomerRequest createCustomerRequest);

}
