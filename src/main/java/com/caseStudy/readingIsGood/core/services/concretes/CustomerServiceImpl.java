package com.caseStudy.readingIsGood.core.services.concretes;

import com.caseStudy.readingIsGood.common.enums.CustomerStates;
import com.caseStudy.readingIsGood.common.enums.ResultCodes;
import com.caseStudy.readingIsGood.common.exceptions.BusinessException;
import com.caseStudy.readingIsGood.common.utilities.AppUtils;
import com.caseStudy.readingIsGood.common.utilities.mapping.ModelMapperService;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.common.utilities.results.SuccessResult;
import com.caseStudy.readingIsGood.core.requests.create.CreateCustomerRequest;
import com.caseStudy.readingIsGood.core.services.abstracts.BaseService;
import com.caseStudy.readingIsGood.core.services.abstracts.CustomerService;
import com.caseStudy.readingIsGood.domain.entities.Customer;
import com.caseStudy.readingIsGood.domain.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl extends BaseService implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(ModelMapperService mapperService, CustomerRepository customerRepository) {
        super(mapperService);
        this.customerRepository = customerRepository;
    }

    @Override
    public Result add(CreateCustomerRequest createCustomerRequest) {
        checkIfCustomerExistsByEmail(createCustomerRequest.getEmail());
        createCustomerRequest.setPassword(AppUtils.encode(createCustomerRequest.getPassword()));
        Customer customer = this.modelMapperService.forRequestMap(createCustomerRequest, Customer.class);
        customer.setState(CustomerStates.Active);
        this.customerRepository.save(customer);
        return new SuccessResult(ResultCodes.CUSTOMER_ADDED.getCode());
    }


    private void checkIfCustomerExistsByEmail(String email) {
        Customer existedCustomer = this.customerRepository.findByEmail(email);
        if(existedCustomer != null){
            throw new BusinessException(ResultCodes.EMAIL_EXISTS.getCode());
        }
    }


}
