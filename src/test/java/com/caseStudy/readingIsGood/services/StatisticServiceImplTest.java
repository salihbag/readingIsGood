package com.caseStudy.readingIsGood.services;

import com.caseStudy.readingIsGood.common.utilities.AppUtils;
import com.caseStudy.readingIsGood.common.utilities.mapping.ModelMapperServiceImpl;
import com.caseStudy.readingIsGood.common.utilities.results.DataResult;
import com.caseStudy.readingIsGood.common.utilities.results.Result;
import com.caseStudy.readingIsGood.common.utilities.results.SuccessDataResult;
import com.caseStudy.readingIsGood.core.responses.GetAllOrdersResponse;
import com.caseStudy.readingIsGood.core.services.abstracts.CustomerService;
import com.caseStudy.readingIsGood.core.services.concretes.StatisticServiceImpl;
import com.caseStudy.readingIsGood.domain.entities.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatisticServiceImplTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private StatisticServiceImpl statisticServiceImpl;

    private static Order ORDER;

    private static GetAllOrdersResponse ORDER_RESPONSE;

    private static List<Order> ORDER_LIST;

    private static List<GetAllOrdersResponse> ORDER_RESPONSE_LIST;

    private static DataResult<List<GetAllOrdersResponse>> ORDER_RESPONSE_LIST_RESULT;

    @BeforeEach
    public void setUp(){
        customerService = mock(CustomerService.class);
        statisticServiceImpl = new StatisticServiceImpl(new ModelMapperServiceImpl(new ModelMapper()), customerService);
    }
    @BeforeAll
    public static void initialize(){
        ORDER = new Order();
        ORDER_LIST = new ArrayList<>();
        ORDER_LIST.add(ORDER);

        ORDER_RESPONSE = new GetAllOrdersResponse();
        ORDER_RESPONSE.setCreatedDate(AppUtils.now());
        ORDER_RESPONSE.setCustomerId(1);
        ORDER_RESPONSE.setTotalPrice(100);
        ORDER_RESPONSE.setTotalBookCount(10);

        ORDER_RESPONSE_LIST = new ArrayList<>();
        ORDER_RESPONSE_LIST.add(ORDER_RESPONSE);

        ORDER_RESPONSE_LIST_RESULT = new SuccessDataResult<>();
        ORDER_RESPONSE_LIST_RESULT.setData(ORDER_RESPONSE_LIST);

    }

    @Test
    public void getCustomerMonthlyStatics_shouldBeSuccess() {
        when(customerService.getAllOrdersByCustomerId(1,Pageable.unpaged())).thenReturn(ORDER_RESPONSE_LIST_RESULT);
        Result result = statisticServiceImpl.getCustomerMonthlyStatics(1);
        Assertions.assertEquals(true, result.isSuccess());
    }


}
