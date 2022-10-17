package com.caseStudy.readingIsGood.api.controllers;

import com.caseStudy.readingIsGood.common.utilities.results.DataResult;
import com.caseStudy.readingIsGood.core.responses.GetAllStatisticsResponse;
import com.caseStudy.readingIsGood.core.services.abstracts.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticService statisticService;

    @Autowired
    public StatisticsController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<List<GetAllStatisticsResponse>>> getAllStatistics(@PathVariable int id){
        return ResponseEntity.ok(this.statisticService.getCustomerMonthlyStatics(id));
    }

}
