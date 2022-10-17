package com.caseStudy.readingIsGood.services;

import com.caseStudy.readingIsGood.common.exceptions.BusinessException;
import com.caseStudy.readingIsGood.common.utilities.mapping.ModelMapperServiceImpl;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.core.requests.create.CreateCustomerRequest;
import com.caseStudy.readingIsGood.core.services.abstracts.OrderService;
import com.caseStudy.readingIsGood.core.services.concretes.CustomerServiceImpl;
import com.caseStudy.readingIsGood.domain.entities.Customer;
import com.caseStudy.readingIsGood.domain.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    private static Customer CUSTOMER;

    private static final String EMAIL="email@email.com";

    private static final String PASSWORD="password";

    @BeforeEach
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        orderService = mock(OrderService.class);
        customerServiceImpl = new CustomerServiceImpl(new ModelMapperServiceImpl(new ModelMapper()), customerRepository, orderService);
    }
    @BeforeAll
    public static void initialize(){
        CUSTOMER = new Customer();
        CUSTOMER.setEmail("email@email.com");
    }

    @Test
    public void addCustomer_shouldBeSuccess() {

        when(customerRepository.findByEmail(EMAIL)).thenReturn(null);
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        createCustomerRequest.setEmail(EMAIL);
        createCustomerRequest.setPassword(PASSWORD);

        Result result = customerServiceImpl.add(createCustomerRequest);
        Assertions.assertEquals(true, result.isSuccess());
    }

    @Test
    public void addCustomer_shouldThrowBusinessException() {

        when(customerRepository.findByEmail(EMAIL)).thenReturn(CUSTOMER);
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        createCustomerRequest.setEmail(EMAIL);
        createCustomerRequest.setPassword(PASSWORD);

        Executable executable = () -> customerServiceImpl.add(createCustomerRequest);
        Assertions.assertThrows(BusinessException.class, executable);
    }

    @Test
    public void getCustomerById_shouldBeSuccess() {
        when(customerRepository.findById(1)).thenReturn(CUSTOMER);
        Result result = customerServiceImpl.getCustomerById(1);
        Assertions.assertEquals(true, result.isSuccess());
    }

    @Test
    public void getAllOrdersByCustomerId_shouldBeSuccess() {
        when(customerRepository.findById(1)).thenReturn(CUSTOMER);
        Result result = customerServiceImpl.getAllOrdersByCustomerId(1, Pageable.unpaged());
        Assertions.assertEquals(true, result.isSuccess());
    }

}
