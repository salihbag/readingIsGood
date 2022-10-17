package com.caseStudy.readingIsGood.core.services.concretes;

import com.caseStudy.readingIsGood.common.utilities.mapping.ModelMapperService;
import com.caseStudy.readingIsGood.common.utilities.results.DataResult;
import com.caseStudy.readingIsGood.common.utilities.results.SuccessDataResult;
import com.caseStudy.readingIsGood.core.responses.GetAllOrdersResponse;
import com.caseStudy.readingIsGood.core.responses.GetAllStatisticsResponse;
import com.caseStudy.readingIsGood.core.services.abstracts.BaseService;
import com.caseStudy.readingIsGood.core.services.abstracts.CustomerService;
import com.caseStudy.readingIsGood.core.services.abstracts.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl extends BaseService implements StatisticService {

    private final CustomerService customerService;

    @Autowired
    public StatisticServiceImpl(ModelMapperService mapperService, CustomerService customerService) {
        super(mapperService);
        this.customerService = customerService;
    }


    @Override
    public DataResult<List<GetAllStatisticsResponse>> getCustomerMonthlyStatics(int customerId) {

        List<GetAllOrdersResponse> customerOrders = customerService.getAllOrdersByCustomerId(customerId, Pageable.unpaged()).getData();

        Map<Month, List<GetAllOrdersResponse>> orderMap = customerOrders.stream()
                .collect(Collectors.groupingBy(order -> order.getCreatedDate().getMonth()));

        List<GetAllStatisticsResponse> statisticList =  orderMap.entrySet().stream()
                .map(entry -> createCustomerMonthlyStatics(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(statisticList);
    }

    private GetAllStatisticsResponse createCustomerMonthlyStatics(Month month, List<GetAllOrdersResponse> orderList) {

        int totalBookCount = 0;
        double totalPurchasedAmount = 0;

        for (GetAllOrdersResponse order : orderList) {
            totalBookCount += order.getTotalBookCount();
            totalPurchasedAmount += order.getTotalPrice();
        }

        return new GetAllStatisticsResponse(month.toString(), orderList.size(), totalBookCount, totalPurchasedAmount);
    }
}
