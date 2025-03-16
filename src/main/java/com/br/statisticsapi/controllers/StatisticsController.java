package com.br.statisticsapi.controllers;


import com.br.statisticsapi.dtos.output.TransactionStatisticsResponse;
import com.br.statisticsapi.services.TransactionsStatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class StatisticsController {

    private final TransactionsStatisticsService transactionsStatisticsService;

    public StatisticsController(TransactionsStatisticsService transactionsStatisticsService) {
        this.transactionsStatisticsService = transactionsStatisticsService;
    }

    @GetMapping
    public ResponseEntity<TransactionStatisticsResponse> getStatisticsInTheLastOneMinute() {
        var statistics = transactionsStatisticsService.getStatisticsInTheLastOneMinute();
        return ResponseEntity.ok(statistics);
    }
}
