package com.caseStudy.readingIsGood.services;

import com.caseStudy.readingIsGood.common.exceptions.BusinessException;
import com.caseStudy.readingIsGood.common.utilities.mapping.ModelMapperServiceImpl;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.core.requests.create.CreateCustomerRequest;
import com.caseStudy.readingIsGood.core.requests.create.CreateOrderRequest;
import com.caseStudy.readingIsGood.core.services.abstracts.BookService;
import com.caseStudy.readingIsGood.core.services.abstracts.CustomerService;
import com.caseStudy.readingIsGood.core.services.abstracts.OrderService;
import com.caseStudy.readingIsGood.core.services.concretes.CustomerServiceImpl;
import com.caseStudy.readingIsGood.core.services.concretes.OrderServiceImpl;
import com.caseStudy.readingIsGood.domain.entities.Customer;
import com.caseStudy.readingIsGood.domain.repositories.BookOrderRelationRepository;
import com.caseStudy.readingIsGood.domain.repositories.CustomerRepository;
import com.caseStudy.readingIsGood.domain.repositories.OrderRepository;
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

public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BookOrderRelationRepository bookOrderRelationRepository;

    @Mock
    private BookService bookService;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    private static Customer CUSTOMER;

    private static final String EMAIL="email@email.com";

    private static final String PASSWORD="password";

    @BeforeEach
    public void setUp() {
        orderRepository = mock(OrderRepository.class);
        bookOrderRelationRepository = mock(BookOrderRelationRepository.class);
        bookService = mock(BookService.class);
        customerService = mock(CustomerService.class);
        orderServiceImpl = new OrderServiceImpl(new ModelMapperServiceImpl(new ModelMapper()),
                orderRepository, bookOrderRelationRepository, bookService, customerService);
    }
    @BeforeAll
    public static void initialize(){
        CUSTOMER = new Customer();
        CUSTOMER.setEmail("email@email.com");
    }

    @Test
    public void addOrder_shouldBeSuccess() {

//        when(customerRepository.findByEmail(EMAIL)).thenReturn(null);
//        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
//
//        Result result = orderServiceImpl.add(createOrderRequest);
//        Assertions.assertEquals(true, result.isSuccess());
    }


}
