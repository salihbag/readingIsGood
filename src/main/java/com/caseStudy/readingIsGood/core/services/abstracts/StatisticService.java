package com.caseStudy.readingIsGood.core.services.abstracts;

import com.caseStudy.readingIsGood.common.utilities.results.DataResult;
import com.caseStudy.readingIsGood.core.responses.GetAllStatisticsResponse;

import java.util.List;

public interface StatisticService {
    DataResult<List<GetAllStatisticsResponse>> getCustomerMonthlyStatics (int customerId);
}
